package by.it_academy.fitness.config;


import by.it_academy.fitness.service.EmailService;
import by.it_academy.fitness.service.api.mail.IEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import java.util.Properties;


@Configuration
public class SpringConfig {
    @Bean
    public IEmailService iEmailService(JavaMailSender javaMailSender) {
        return new EmailService(javaMailSender);
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.mail.ru");
        mailSender.setPort(465);
        mailSender.setUsername("ivanivanov2023_18@mail.ru");
        mailSender.setPassword("CzgX7LYBBE0GfaQPrZL6");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

}
