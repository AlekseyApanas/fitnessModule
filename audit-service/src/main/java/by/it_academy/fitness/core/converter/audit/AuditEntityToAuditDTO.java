package by.it_academy.fitness.core.converter.audit;

import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.entity.AuditEntity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditDTO implements Converter<AuditEntity, AuditDTO> {
    @Override
    public AuditDTO convert(AuditEntity auditEntity) {
        return new AuditDTO(auditEntity.getUuid(),
                auditEntity.getDtCreate(),
                new UserDTO(auditEntity.getUuidUser(),
                        auditEntity.getMail(),
                        auditEntity.getFio(),
                        auditEntity.getRole()),
                auditEntity.getText(),
                auditEntity.getType(),
                auditEntity.getUuidService());
    }
}