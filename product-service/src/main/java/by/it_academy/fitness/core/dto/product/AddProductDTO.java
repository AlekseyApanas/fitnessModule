package by.it_academy.fitness.core.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class AddProductDTO {
    @NotBlank
    private String title;
    @PositiveOrZero

    private Integer weight;
    @PositiveOrZero

    private Integer calories;
    @PositiveOrZero

    private Double proteins;
    @PositiveOrZero

    private Double fats;
    @PositiveOrZero
    private Double carbohydrates;

    public AddProductDTO(String title, Integer weight, Integer calories, double proteins, double fats, double carbohydrates) {
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public String getTitle() {
        return title;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getFats() {
        return fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }
}
