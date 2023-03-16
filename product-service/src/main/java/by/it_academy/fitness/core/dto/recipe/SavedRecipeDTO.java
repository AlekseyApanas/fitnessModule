package by.it_academy.fitness.core.dto.recipe;

import java.time.Instant;

public class SavedRecipeDTO {
    private AddRecipeDTO addRecipeDTO;
    private Instant dtCreate;
    private Instant dtUpdate;

    public AddRecipeDTO getAddRecipeDTO() {
        return addRecipeDTO;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public SavedRecipeDTO(AddRecipeDTO addRecipeDTO) {
        this.addRecipeDTO = addRecipeDTO;
        this.dtCreate = Instant.now();
        this.dtUpdate = this.dtCreate;
    }
}
