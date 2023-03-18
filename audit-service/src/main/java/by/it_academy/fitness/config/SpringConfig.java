package by.it_academy.fitness.config;

import by.it_academy.fitness.dao.api.user.IAuditDao;
import by.it_academy.fitness.service.AuditService;
import by.it_academy.fitness.service.api.user.IAuditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SpringConfig {
    @Bean
    public IAuditService auditService(IAuditDao dao, ConversionService conversionService) {
        return new AuditService(dao, conversionService);
    }
}
