package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.email.EmailDTO;
import by.it_academy.fitness.service.api.mail.IEmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailService implements IEmailService {
    public JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleEmail(EmailDTO emailDTO) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("alsha.alsha.23@mail.ru");
            simpleMailMessage.setTo(emailDTO.getTo());
            simpleMailMessage.setSubject(emailDTO.getSubject());
            simpleMailMessage.setText("Для подтверждения перейдите по ссылке :http://localhost/api/v1/users/verification?code=" + emailDTO.getText() + "&mail=" + emailDTO.getTo());
            emailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}
