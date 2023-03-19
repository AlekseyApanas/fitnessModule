package by.it_academy.fitness.service.api.product;

import java.util.UUID;

public interface IAuditService {
    void checkUserAndSend(String actions, String type, UUID uuidService);

}
