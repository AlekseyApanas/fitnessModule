package by.it_academy.fitness.core.converter.user;

import by.it_academy.fitness.core.dto.user.AddUserDTO;
import by.it_academy.fitness.core.dto.user.SavedUserDTO;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.StatusEntity;
import by.it_academy.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AddUserDTOToUserEntity implements Converter<AddUserDTO, UserEntity> {

    @Override
    public UserEntity convert(AddUserDTO addUserDTO) {
        SavedUserDTO savedUserDTO = new SavedUserDTO(addUserDTO);
        return new UserEntity(savedUserDTO.getUserDTO().getMail(),
                savedUserDTO.getUserDTO().getFio(),
                savedUserDTO.getUserDTO().getPassword(),
                savedUserDTO.getDtCreate(),
                savedUserDTO.getDtUpdate(),
                new RoleEntity(savedUserDTO.getUserDTO().getRole()),
                new StatusEntity(savedUserDTO.getUserDTO().getStatus()));
    }
}
