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
                new UserDTO(auditEntity.getUserEntity().getUuid(),
                        auditEntity.getUserEntity().getMail(),
                        auditEntity.getUserEntity().getFio(),
                        auditEntity.getUserEntity().getRole().getRole()),
                auditEntity.getText(),
                auditEntity.getType().getType(),
                auditEntity.getUuidService());
    }
}