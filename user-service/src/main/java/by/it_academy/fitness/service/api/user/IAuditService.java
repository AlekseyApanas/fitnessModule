package by.it_academy.fitness.service.api.user;

import by.it_academy.fitness.core.dto.user.UserDTO;

public interface IAuditService {
    void checkUserAndSend(String mail, String actions, UserDTO userDTO);

}
