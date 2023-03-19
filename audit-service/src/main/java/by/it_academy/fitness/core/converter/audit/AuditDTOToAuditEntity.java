package by.it_academy.fitness.core.converter.audit;

import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO auditDTO) {
        return new AuditEntity(
                auditDTO.getDtCreate(),
                auditDTO.getUser().getUuidUser(),
                auditDTO.getUser().getMail(),
                auditDTO.getUser().getFio(),
                auditDTO.getUser().getRole(),
                auditDTO.getText(),
                auditDTO.getType(),
                auditDTO.getUuidService());
    }
}
