package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.user.AddUserDTO;
import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.core.dto.user.UserLogInDTO;
import by.it_academy.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.core.exception.ValidException;
import by.it_academy.fitness.dao.api.user.IAuthenticationDao;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserEntity;
import by.it_academy.fitness.service.api.user.IAuthenticationService;
import by.it_academy.fitness.service.api.user.IUserService;
import by.it_academy.fitness.userEnum.UserStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

public class AuthenticationService implements IAuthenticationService {
    @Value("${spring.data.redis.urlemail}")
    private String url;
    private final IAuthenticationDao dao;
    private final ConversionService conversionService;
    private final BCryptPasswordEncoder encoder;
    private final IUserService iUserService;

    public AuthenticationService(IAuthenticationDao dao, ConversionService conversionService, BCryptPasswordEncoder encoder, IUserService iUserService) {
        this.dao = dao;
        this.conversionService = conversionService;
        this.encoder = encoder;
        this.iUserService = iUserService;
    }

    public UserDTO logIn(UserLogInDTO userLogInDTO) {
        UserEntity userEntity = dao.findByMail(userLogInDTO.getMail().toLowerCase());
        if (userEntity == null) {
            throw new NotFoundException("Такого юзера не существует");
        }
        if (!encoder.matches(userLogInDTO.getPassword(), userEntity.getPassword())) {
            throw new ValidException("Введены некорректные данные");
        }
        return conversionService.convert(userEntity, UserDTO.class);
    }


    @Override
    public void registration(UserRegistrationDTO userRegistrationDTO) {
        UserEntity userEntity = dao.findByMail(userRegistrationDTO.getMail().toLowerCase());
        if (userEntity != null) {
            throw new CheckDoubleException("Юзер с таким mail уже существует");
        } else {
            iUserService.create(new AddUserDTO(userRegistrationDTO.getMail().toLowerCase(), userRegistrationDTO.getFio(), userRegistrationDTO.getPassword()));
            UUID code = UUID.randomUUID();
            userEntity = dao.findByMail(userRegistrationDTO.getMail().toLowerCase());
            userEntity.setCode(code.toString());
            dao.save(userEntity);
            post(userRegistrationDTO.getMail().toLowerCase(), "Verification", code.toString());
        }
    }

    @Override
    public void verification(String code, String mail) {
        UserEntity userEntity = dao.findByMail(mail.toLowerCase());
        if (userEntity != null && code.equals(userEntity.getCode())) {
            userEntity.setStatus(new StatusEntity((UserStatus.ACTIVATED)));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new NotFoundException("Такого юзера не существует");
    }

    private void post(String to, String subject, String text) {
        try {
            JSONObject jo = new JSONObject();
            jo.put("subject", subject);
            jo.put("to", to);
            jo.put("text", text);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jo.toString())).build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
