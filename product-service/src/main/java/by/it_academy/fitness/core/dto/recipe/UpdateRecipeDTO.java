package by.it_academy.fitness.core.dto.recipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.Instant;
import java.util.UUID;

public class UpdateRecipeDTO {
    @NotEmpty
    private AddRecipeDTO addRecipeDTO;
    @NonNull
    private Instant dtUpdate;
    @NotBlank
    private UUID uuid;

    public UpdateRecipeDTO(AddRecipeDTO addRecipeDTO, Instant dtUpdate, UUID uuid) {
        this.addRecipeDTO = addRecipeDTO;
        this.dtUpdate = dtUpdate;
        this.uuid = uuid;
    }

    public AddRecipeDTO getAddRecipeDTO() {
        return addRecipeDTO;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }
}
