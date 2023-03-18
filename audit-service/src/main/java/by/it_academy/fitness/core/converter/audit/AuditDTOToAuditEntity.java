package by.it_academy.fitness.core.converter.audit;

import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.entity.AuditEntity;
import by.it_academy.fitness.entity.RoleEntity;
import by.it_academy.fitness.entity.TypeEntity;
import by.it_academy.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO auditDTO) {
        RoleEntity roleEntity = new RoleEntity(auditDTO.getUser().getRole());
        UserEntity userEntity = new UserEntity(auditDTO.getUser().getUuid(),
                auditDTO.getUser().getMail(),
                auditDTO.getUser().getFio(),
                roleEntity);
        return new AuditEntity(
                auditDTO.getDtCreate(),
                userEntity,
                auditDTO.getText(),
                new TypeEntity(auditDTO.getType()),
                auditDTO.getUuidService());
    }
}
