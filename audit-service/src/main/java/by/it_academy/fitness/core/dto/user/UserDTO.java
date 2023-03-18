package by.it_academy.fitness.core.dto.user;

import by.it_academy.fitness.userEnum.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.*;

public class UserDTO {
    @NotBlank
    @JsonProperty("uuid")
    private UUID uuid;
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

    public UserDTO(UUID uuid, String mail, String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(uuid, userDTO.uuid) && Objects.equals(mail, userDTO.mail) && Objects.equals(fio, userDTO.fio) && Objects.equals(role, userDTO.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, mail, fio, role);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                '}';
    }
}
