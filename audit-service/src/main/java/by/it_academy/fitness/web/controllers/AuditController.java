package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.user.AuditDTO;
import by.it_academy.fitness.core.dto.page.PageDTO;

import by.it_academy.fitness.service.api.user.IAuditService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private final IAuditService iAuditService;


    public AuditController(IAuditService iAuditService) {
        this.iAuditService = iAuditService;
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<AuditDTO> get(@PathVariable("uuid") UUID userUUID) {
        return ResponseEntity.status(HttpStatus.OK).body(iAuditService.get(userUUID));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid AuditDTO auditDTO) {
        iAuditService.create(new AuditDTO(auditDTO.getUser(), auditDTO.getText(), auditDTO.getType(), auditDTO.getUuidService()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> get(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "20") @Min(0) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(iAuditService.get(page, size));
    }
}
