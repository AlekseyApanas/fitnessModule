package by.it_academy.fitness.core.converter.user;


import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.entity.UserEntity;
import by.it_academy.fitness.userEnum.UserRole;
import by.it_academy.fitness.userEnum.UserStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
@Component
public class UserEntityToUserDTO implements Converter<UserEntity, UserDTO> {
    @Override
    public UserDTO convert(UserEntity userEntity) {
        UUID uuid = userEntity.getUuid();
        Instant dtCreate = userEntity.getDtCreate();
        Instant dtUpdate = userEntity.getDtUpdate();
        String mail = userEntity.getMail();
        String fio = userEntity.getFio();
        UserRole role = userEntity.getRole().getRole();
        UserStatus status = userEntity.getStatus().getStatus();
        return new UserDTO(uuid, dtCreate, dtUpdate, mail, fio, role, status);
    }
}
