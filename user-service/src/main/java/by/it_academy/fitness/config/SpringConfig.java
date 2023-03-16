package by.it_academy.fitness.config;

import by.it_academy.fitness.dao.api.user.IAuthenticationDao;
import by.it_academy.fitness.dao.api.user.IUserDao;
import by.it_academy.fitness.service.*;
import by.it_academy.fitness.service.api.user.IAuthenticationService;
import by.it_academy.fitness.service.api.user.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@Configuration
public class SpringConfig {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserService userService(IUserDao dao, ConversionService conversionService, PasswordEncoder encoder) {
        return new UserService(dao, conversionService, encoder);
    }

    @Bean
    public IAuthenticationService authenticationService(IAuthenticationDao dao, ConversionService conversionService, BCryptPasswordEncoder encoder, IUserService iUserService) {
        return new AuthenticationService(dao, conversionService, encoder, iUserService);
    }

}
