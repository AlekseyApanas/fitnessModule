package by.it_academy.fitness.core.dto.user;

import by.it_academy.fitness.core.converter.InstantToLongConverter;
import by.it_academy.fitness.userEnum.EntityType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.Instant;
import java.util.UUID;

public class AuditDTO {

    @JsonProperty("uuid")
    private UUID uuid;
    @JsonSerialize(converter = InstantToLongConverter.class)
    @JsonProperty("dtCreate")
    private Instant dtCreate;
    @NotNull
    @JsonProperty("user")
    private UserDTO user;
    @NotBlank
    @JsonProperty("text")
    private String text;

    @JsonProperty("type")
    private EntityType type;
    @NotBlank
    @JsonProperty("uuidService")
    private String uuidService;

    public AuditDTO() {
    }

    public AuditDTO(UserDTO user, String text, EntityType type, String uuidService) {
        this.dtCreate = Instant.now();
        this.user = user;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public AuditDTO(UUID uuid, Instant dtCreate, UserDTO user, String text, EntityType type, String uuidService) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getText() {
        return text;
    }

    public EntityType getType() {
        return type;
    }

    public String getUuidService() {
        return uuidService;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public UserDTO getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "AuditDTO{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", uuidService='" + uuidService + '\'' +
                '}';
    }
}
