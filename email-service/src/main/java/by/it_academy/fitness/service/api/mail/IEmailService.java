package by.it_academy.fitness.service.api.mail;

import by.it_academy.fitness.core.dto.email.EmailDTO;

public interface IEmailService {
    public void sendSimpleEmail(EmailDTO emailDTO);
}
