package by.it_academy.fitness.core.dto.recipe.ingredient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public class AddIngredientDTO {
    @NotBlank
    private UUID product;
    @PositiveOrZero
    private Integer weight;

    public AddIngredientDTO(UUID product, Integer weight) {
        this.product = product;
        this.weight = weight;
    }

    public AddIngredientDTO() {
    }

    public UUID getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }
}
