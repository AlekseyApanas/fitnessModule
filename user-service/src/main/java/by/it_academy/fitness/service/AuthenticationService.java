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
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class AuthenticationService implements IAuthenticationService {

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
        UserEntity userEntity = dao.findByMail(userLogInDTO.getMail());
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
        UserEntity userEntity = dao.findByMail(userRegistrationDTO.getMail());
        if (userEntity != null) {
            throw new CheckDoubleException("Юзер с таким mail уже существует");
        } else {
            iUserService.create(new AddUserDTO(userRegistrationDTO.getMail(), userRegistrationDTO.getFio(), userRegistrationDTO.getPassword()));
            UUID code = UUID.randomUUID();
            userEntity = dao.findByMail(userRegistrationDTO.getMail());
            userEntity.setCode(code.toString());
            dao.save(userEntity);
            post(userRegistrationDTO.getMail(), "Verification", code.toString());
            /*emailService.sendSimpleEmail(userRegistrationDTO.getMail(), "Verification", code.toString());*/
        }
    }

    @Override
    public void verification(String code, String mail) {
        UserEntity userEntity = dao.findByMail(mail);
        if (userEntity != null && code.equals(userEntity.getCode())) {
            userEntity.setStatus(new StatusEntity((UserStatus.ACTIVATED)));
            userEntity.setCode(null);
            dao.save(userEntity);
        } else throw new NotFoundException("Такого юзера не существует");
    }

    private void post(String to, String subject, String text) {
        try {
            JSONObject jo = new JSONObject();
            jo.put("to", to);
            jo.put("subject", subject);

            jo.put("text", text);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://email-service:8080/api/v1/mail"))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jo.toString())).build();
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
