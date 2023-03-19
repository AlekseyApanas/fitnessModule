package by.it_academy.fitness.core.dto.recipe;

import java.time.Instant;
import java.util.UUID;

public class SavedRecipeDTO {
    private UUID uuid;
    private AddRecipeDTO addRecipeDTO;
    private Instant dtCreate;
    private Instant dtUpdate;

    public SavedRecipeDTO(AddRecipeDTO addRecipeDTO) {
        this.uuid = UUID.randomUUID();
        this.addRecipeDTO = addRecipeDTO;
        this.dtCreate = Instant.now();
        this.dtUpdate = this.dtCreate;
    }

    public AddRecipeDTO getAddRecipeDTO() {
        return addRecipeDTO;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public UUID getUuid() {
        return uuid;
    }
}
