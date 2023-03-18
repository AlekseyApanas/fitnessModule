package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.dao.api.user.IAuditDao;
import by.it_academy.fitness.entity.AuditEntity;
import by.it_academy.fitness.service.api.user.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class AuditService implements IAuditService {
    private final IAuditDao dao;
    private final ConversionService conversionService;

    public AuditService(IAuditDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }

    @Override
    public void create(AuditDTO auditDTO) {
        dao.save(Objects.requireNonNull(conversionService.convert(auditDTO, AuditEntity.class)));
    }

    @Override
    public PageDTO<AuditDTO> get(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<AuditEntity> all = dao.findAll(paging);
        List<AuditDTO> auditDTOS = all.getContent().stream()
                .map(s -> conversionService.convert(s, AuditDTO.class))
                .collect(Collectors.toList());
        return new PageDTO<>(page, size, all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.getNumberOfElements(), all.isLast(), auditDTOS);
    }


    @Override
    public AuditDTO get(UUID uuid) {
        AuditEntity auditEntity = this.dao.findById(uuid).orElseThrow(() -> new NotFoundException("Такого юзера не существует"));
        return conversionService.convert(auditEntity, AuditDTO.class);
    }
}
