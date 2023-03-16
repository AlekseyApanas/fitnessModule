package by.it_academy.fitness.core.dto.recipe;

import by.it_academy.fitness.core.dto.recipe.ingredient.AddIngredientDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class AddRecipeDTO {
    @NotBlank
    private String title;
    @NotEmpty
    private List<AddIngredientDTO> composition;

    public String getTitle() {
        return title;
    }

    public List<AddIngredientDTO> getComposition() {
        return composition;
    }

    public AddRecipeDTO() {
    }

    public AddRecipeDTO(String title, List<AddIngredientDTO> composition) {
        this.title = title;
        this.composition = composition;
    }
}
