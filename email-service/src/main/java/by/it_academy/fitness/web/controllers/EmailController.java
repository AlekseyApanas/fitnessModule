package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.email.EmailDTO;
import by.it_academy.fitness.service.api.mail.IEmailService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mail")
public class EmailController {

    private final IEmailService service;

    public EmailController(IEmailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> send(@Valid @RequestBody EmailDTO emailDTO) {
        service.sendSimpleEmail(emailDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

