package by.it_academy.fitness.service.api.user;

import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.core.dto.user.UserLogInDTO;
import by.it_academy.fitness.core.dto.user.UserRegistrationDTO;

public interface IAuthenticationService {
    UserDTO logIn(UserLogInDTO userLogInDTO);

    void registration(UserRegistrationDTO userRegistrationDTO);

    void verification(String code,String mail);
}
