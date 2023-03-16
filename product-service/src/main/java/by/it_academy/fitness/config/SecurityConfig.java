package by.it_academy.fitness.config;

import by.it_academy.fitness.web.filter.JwtFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    private final JwtFilter filter;

    public SecurityConfig(JwtFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and();

        // Set permissions on endpoints
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(HttpMethod.GET,"/api/v1/product").authenticated()
                        .requestMatchers(HttpMethod.GET,"/api/v1/recipe").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/v1/product/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/recipe/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/product/**").hasAnyAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/v1/recipe/**").hasAnyAuthority("ROLE_ADMIN")
                )
                .httpBasic(withDefaults());
        http.addFilterBefore(
                filter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }
}