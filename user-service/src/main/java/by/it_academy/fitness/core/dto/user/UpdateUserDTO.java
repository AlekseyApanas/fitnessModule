package by.it_academy.fitness.core.dto.user;

import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.UUID;

public class UpdateUserDTO {
    @NotEmpty
    private AddUserDTO userDTO;
    @NotEmpty
    private Instant dtUpdate;
    @NotEmpty
    private UUID uuid;

    public UpdateUserDTO(AddUserDTO userDTO, Instant dtUpdate, UUID uuid) {
        this.userDTO = userDTO;
        this.dtUpdate = dtUpdate;
        this.uuid = uuid;
    }

    public AddUserDTO getUserDTO() {
        return userDTO;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }
}
