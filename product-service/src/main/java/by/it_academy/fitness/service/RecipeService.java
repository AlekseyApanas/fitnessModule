package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.recipe.AddRecipeDTO;
import by.it_academy.fitness.core.dto.recipe.RecipeDTO;
import by.it_academy.fitness.core.dto.recipe.SavedRecipeDTO;
import by.it_academy.fitness.core.dto.recipe.ingredient.AddIngredientDTO;
import by.it_academy.fitness.core.dto.recipe.UpdateRecipeDTO;
import by.it_academy.fitness.core.dto.recipe.ingredient.IngredientDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.CheckVersionException;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.dao.api.product.IRecipeDao;
import by.it_academy.fitness.entity.IngredientEntity;
import by.it_academy.fitness.entity.ProductEntity;
import by.it_academy.fitness.entity.RecipeEntity;
import by.it_academy.fitness.service.api.product.IProductService;
import by.it_academy.fitness.service.api.product.IRecipeService;

import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService implements IRecipeService {
    private final IRecipeDao dao;

    private final IProductService productService;
    private final ConversionService conversionService;

    public RecipeService(IRecipeDao dao, IProductService productService, ConversionService conversionService) {
        this.dao = dao;
        this.productService = productService;
        this.conversionService = conversionService;
    }

    @Override
    public void create(AddRecipeDTO recipeDTO) {
        RecipeEntity recipeEntity = dao.findByTitle(recipeDTO.getTitle());
        if (recipeEntity != null) {
            throw new CheckDoubleException("Рецепт с таким названием уже существует");
        } else {
            List<AddIngredientDTO> ingredientDTOList = recipeDTO.getComposition();
            List<IngredientEntity> list = ingredientDTOList.stream()
                    .map(s -> new IngredientEntity(conversionService.convert(productService.get(s.getProduct()), ProductEntity.class),
                            s.getWeight())).collect(Collectors.toList());
            SavedRecipeDTO savedRecipeDTO = new SavedRecipeDTO(recipeDTO);
            dao.save(new RecipeEntity(savedRecipeDTO.getDtCreate(),
                    savedRecipeDTO.getDtUpdate(), savedRecipeDTO.getAddRecipeDTO().getTitle(),
                    list));
        }
    }

    @Override
    public PageDTO<RecipeDTO> get(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<RecipeEntity> all = dao.findAll(paging);
        List<RecipeDTO> recipePages = new ArrayList<>();
        List<RecipeEntity> content = all.getContent();
        for (RecipeEntity recipeEntity : content) {
            List<IngredientDTO> ingredientDTOList = recipeEntity.getComposition().stream().
                    map(s -> new IngredientDTO(productService.get(
                            s.getProduct().getUuid()),
                            s.getWeight(),
                            s.getWeight() * s.getProduct().getCalories() / s.getProduct().getWeight(),
                            (s.getWeight() * (double) s.getProduct().getCalories() / s.getProduct().getWeight()),
                            (s.getWeight() * (double) s.getProduct().getCalories() / s.getProduct().getWeight()),
                            (s.getWeight() * (double) s.getProduct().getCalories() / s.getProduct().getWeight())
                    )).collect(Collectors.toList());
            Integer weight = ingredientDTOList.stream().mapToInt(IngredientDTO::getWeight).sum();
            Integer calories = ingredientDTOList.stream().mapToInt(IngredientDTO::getCalories).sum();
            Double proteins = ingredientDTOList.stream().mapToDouble(IngredientDTO::getProteins).sum();
            Double fats = ingredientDTOList.stream().mapToDouble(IngredientDTO::getFats).sum();
            Double carbohydrates = ingredientDTOList.stream().mapToDouble(IngredientDTO::getCarbohydrates).sum();
            recipePages.add(new RecipeDTO(recipeEntity.getUuid(), recipeEntity.getDtCreate(), recipeEntity.getDtUpdate(), recipeEntity.getTitle(), ingredientDTOList,
                    weight, calories, proteins, fats, carbohydrates));
        }
        return new PageDTO<>(page, size, all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.getNumberOfElements(), all.isLast(), recipePages);
    }


    @Override
    public void update(UpdateRecipeDTO recipeDTO) {
        RecipeEntity recipeEntity = dao.findById(recipeDTO.getUuid()).orElseThrow(() -> new NotFoundException("Такого рецепта не существует"));
        if (recipeDTO.getDtUpdate().toEpochMilli() == recipeEntity.getDtUpdate().toEpochMilli()) {
            List<AddIngredientDTO> ingredientDTOList = recipeDTO.getAddRecipeDTO().getComposition();
            List<IngredientEntity> list = ingredientDTOList.stream()
                    .map(s -> new IngredientEntity(conversionService.convert(productService.get(s.getProduct()), ProductEntity.class),
                            s.getWeight())).collect(Collectors.toList());
            recipeEntity.setTitle(recipeDTO.getAddRecipeDTO().getTitle());
            recipeEntity.setComposition(list);
            dao.save(recipeEntity);
        } else throw new CheckVersionException("Такой версии не существует");
    }
}
