package by.it_academy.fitness.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class UserLogInDTO {
    @NotBlank
    @Email
    private String mail;
    @NotBlank
    private String password;

    public UserLogInDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public UserLogInDTO() {
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogInDTO that = (UserLogInDTO) o;
        return Objects.equals(mail, that.mail) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password);
    }

    @Override
    public String toString() {
        return "UserLogInDTO{" +
                "mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
