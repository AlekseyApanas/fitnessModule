package by.it_academy.fitness.core.dto.recipe.ingredient;

import by.it_academy.fitness.core.converter.DoubleToBigDecimalConverter;
import by.it_academy.fitness.core.dto.product.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public class IngredientDTO {

    @JsonProperty("product")
    private ProductDTO product;
    @JsonProperty("weight")
    private Integer weight;
    @JsonProperty("calories")
    private Integer calories;
    @JsonProperty("proteins")
    @JsonSerialize(converter = DoubleToBigDecimalConverter.class)
    private Double proteins;
    @JsonProperty("fats")
    @JsonSerialize(converter = DoubleToBigDecimalConverter.class)
    private Double fats;
    @JsonProperty("carbohydrates")
    @JsonSerialize(converter = DoubleToBigDecimalConverter.class)
    private Double carbohydrates;

    public IngredientDTO() {
    }

    public IngredientDTO(ProductDTO product, Integer weight, Integer calories, Double proteins, Double fats, Double carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public ProductDTO getProduct() {
        return product;
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
