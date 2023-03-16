package by.it_academy.fitness.core.dto.user;

import by.it_academy.fitness.core.exception.validator.ValueOfEnum;
import by.it_academy.fitness.userEnum.UserRole;
import by.it_academy.fitness.userEnum.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class AddUserDTO {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String fio;
    @NotBlank
    private String password;
    @ValueOfEnum(enumClass = UserRole.class)
    private String role;
    @ValueOfEnum(enumClass = UserStatus.class)
    private String status;

    public AddUserDTO(String mail, String fio, String password, String role, String status) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public AddUserDTO(String mail, String fio, String password) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.role = UserRole.USER.toString();
        this.status = UserStatus.WAITING_ACTIVATION.toString();
    }

    public AddUserDTO() {
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return UserRole.valueOf(role);
    }

    public UserStatus getStatus() {
        return UserStatus.valueOf(status);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserDTO userDTO = (AddUserDTO) o;
        return Objects.equals(mail, userDTO.mail) && Objects.equals(fio, userDTO.fio) && Objects.equals(password, userDTO.password) && role == userDTO.role && status == userDTO.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, password, role, status);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}

