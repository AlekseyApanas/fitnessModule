package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.core.dto.user.UpdateUserDTO;
import by.it_academy.fitness.core.dto.user.AddUserDTO;
import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.CheckVersionException;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.dao.api.user.IUserDao;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserEntity;
import by.it_academy.fitness.service.api.user.IUserService;
import by.it_academy.fitness.web.utils.UserHolder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


public class UserService implements IUserService {

    private final IUserDao dao;
    private final ConversionService conversionService;
    private final PasswordEncoder encoder;

    public UserService(IUserDao dao, ConversionService conversionService, PasswordEncoder encoder) {
        this.dao = dao;
        this.conversionService = conversionService;
        this.encoder = encoder;
    }


    @Override
    public void create(AddUserDTO userDTO) {
        UserEntity userEntity = dao.findByMail(userDTO.getMail());
        if (userEntity != null) {
            throw new CheckDoubleException("Юзер с таким mail уже существует");
        } else {
            String encode = encoder.encode(userDTO.getPassword());
            userDTO.setPassword(encode);
            userEntity = conversionService.convert(userDTO, UserEntity.class);
            dao.save(Objects.requireNonNull(userEntity));
        }
        checkUserAndSend(userDTO.getMail(), "Создана запись в журнале пользователей");
    }

    @Override
    public PageDTO<UserDTO> get(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<UserEntity> all = dao.findAll(paging);
        List<UserDTO> usersPages = all.getContent().stream()
                .map(s -> conversionService.convert(s, UserDTO.class))
                .collect(Collectors.toList());
        return new PageDTO<>(page, size, all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.getNumberOfElements(), all.isLast(), usersPages);
    }


    @Override
    public UserDTO get(UUID uuid) {
        UserEntity userEntity = this.dao.findById(uuid).orElseThrow(() -> new NotFoundException("Такого юзера не существует"));
        return conversionService.convert(userEntity, UserDTO.class);
    }

    @Override
    public void update(UpdateUserDTO updateUserDto) {
        UserEntity userEntity = dao.findById(updateUserDto.getUuid()).orElseThrow(() -> new NotFoundException("Такого юзера не существует"));
        if (userEntity.getDtUpdate().toEpochMilli() == updateUserDto.getDtUpdate().toEpochMilli()) {
            userEntity.setMail(updateUserDto.getUserDTO().getMail());
            userEntity.setFio(updateUserDto.getUserDTO().getFio());
            userEntity.setPassword(updateUserDto.getUserDTO().getPassword());
            userEntity.setRole(new RoleEntity(updateUserDto.getUserDTO().getRole()));
            userEntity.setStatus(new StatusEntity(updateUserDto.getUserDTO().getStatus()));
            dao.save(userEntity);
            checkUserAndSend(updateUserDto.getUserDTO().getMail(), "Обновлена запись в журнале пользователей");
        } else throw new CheckVersionException("Такой версии не существует");
    }

    @Override
    public UserDTO getUser(String mail) {
        UserEntity userEntity = this.dao.findByMail(mail);
        if (userEntity == null) {
            throw new NotFoundException("Такого юзера не существует");
        }
        return conversionService.convert(userEntity, UserDTO.class);
    }

    private void checkUserAndSend(String mail, String actions) {
        UserHolder userHolder = new UserHolder();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO user = principal instanceof String ? getUser(mail) : userHolder.getUser();
        sendAudit(getUser(mail), user.getUuid(), actions);
    }

    private void sendAudit(UserDTO userDto, UUID uuid, String actions) {
        try {
            JSONObject user = new JSONObject();
            user.put("uuidUser", userDto.getUuid());
            user.put("mail", userDto.getMail());
            user.put("fio", userDto.getFio());
            user.put("role", userDto.getRole());
            JSONObject object = new JSONObject();
            object.put("user", user);
            object.put("text", actions);
            object.put("type", "USER");
            object.put("uuidService", uuid);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://audit-service:8080/api/v1/audit"))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
