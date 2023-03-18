package by.it_academy.fitness.web.utils;

import by.it_academy.fitness.core.dto.user.UserDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDTO getUser() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
