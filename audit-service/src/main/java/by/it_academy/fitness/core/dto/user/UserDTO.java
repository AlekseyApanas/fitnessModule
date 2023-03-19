package by.it_academy.fitness.core.dto.user;

import by.it_academy.fitness.core.dto.exception.validator.ValueOfEnum;
import by.it_academy.fitness.userEnum.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.*;

public class UserDTO {
    @NotBlank
    @JsonProperty("uuidUser")
    private UUID uuidUser;
    @Email
    @NotBlank
    @JsonProperty("mail")
    private String mail;
    @NotBlank
    @JsonProperty("fio")
    private String fio;

    @JsonProperty("role")
    private UserRole role;

    public UserDTO() {
    }

    public UserDTO(UUID uuidUser, String mail, String fio, UserRole role) {
        this.uuidUser = uuidUser;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuidUser() {
        return uuidUser;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }


}
