package by.it_academy.fitness.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class VerificationDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String code;

    public VerificationDTO() {
    }

    public VerificationDTO(String mail, String code) {
        this.mail = mail;
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public String getCode() {
        return code;
    }
}
