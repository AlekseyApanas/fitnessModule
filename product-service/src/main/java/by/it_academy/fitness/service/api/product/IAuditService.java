package by.it_academy.fitness.service.api.product;

import by.it_academy.fitness.core.dto.audit.UserDTO;

import java.util.UUID;

public interface IAuditService {
    void checkUserAndSend(String actions);

}
