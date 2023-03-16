package by.it_academy.fitness.core.converter.user;

import by.it_academy.fitness.core.dto.user.SavedUserDTO;
import by.it_academy.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDTOToUserEntity implements Converter<UserRegistrationDTO, UserEntity> {


    @Override
    public UserEntity convert(UserRegistrationDTO userRegistrationDTO) {
        SavedUserDTO savedUserDTO = new SavedUserDTO(userRegistrationDTO);
        return new UserEntity(savedUserDTO.getUserRegistrationDTO().getMail(),
                savedUserDTO.getUserRegistrationDTO().getFio(),
                savedUserDTO.getUserRegistrationDTO().getPassword(),
                savedUserDTO.getDtCreate(),
                savedUserDTO.getDtUpdate(),
                new RoleEntity(savedUserDTO.getRole()),
                new StatusEntity(savedUserDTO.getStatus()));
    }
}
