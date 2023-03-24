package by.it_academy.fitness.service.api.product;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.recipe.AddRecipeDTO;
import by.it_academy.fitness.core.dto.recipe.UpdateRecipeDTO;

import java.util.UUID;


public interface IRecipeService<T> {
    UUID create(AddRecipeDTO recipeDTO);

    PageDTO<T> get(int page, int size);

    UUID update(UpdateRecipeDTO recipeDTO);
}
