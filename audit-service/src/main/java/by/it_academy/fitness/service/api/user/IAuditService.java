package by.it_academy.fitness.service.api.user;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.core.dto.user.UserDTO;

import java.util.UUID;

public interface IAuditService <T>{
    void create(AuditDTO auditDTO);

    PageDTO<T> get(int page, int size);

    AuditDTO get(UUID id);

}
