package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.audit.UserDTO;
import by.it_academy.fitness.core.dto.audit.UserHolder;
import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.product.ProductDTO;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
            checkIngredient(recipeDTO.getComposition());
            List<AddIngredientDTO> ingredientDTOList = recipeDTO.getComposition();
            List<IngredientEntity> list = ingredientDTOList.stream()
                    .map(s -> new IngredientEntity(conversionService.convert(productService.get(s.getProduct()), ProductEntity.class),
                            s.getWeight())).collect(Collectors.toList());
            SavedRecipeDTO savedRecipeDTO = new SavedRecipeDTO(recipeDTO);
            dao.save(new RecipeEntity(savedRecipeDTO.getDtCreate(),
                    savedRecipeDTO.getDtUpdate(), savedRecipeDTO.getAddRecipeDTO().getTitle(),
                    list));
            checkUserAndSend("Создана запись в журнале рецептов");
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
        checkIngredient(recipeDTO.getAddRecipeDTO().getComposition());
        if (recipeDTO.getDtUpdate().toEpochMilli() == recipeEntity.getDtUpdate().toEpochMilli()) {
            List<AddIngredientDTO> ingredientDTOList = recipeDTO.getAddRecipeDTO().getComposition();
            List<IngredientEntity> list = ingredientDTOList.stream()
                    .map(s -> new IngredientEntity(conversionService.convert(productService.get(s.getProduct()), ProductEntity.class),
                            s.getWeight())).collect(Collectors.toList());
            recipeEntity.setTitle(recipeDTO.getAddRecipeDTO().getTitle());
            recipeEntity.setComposition(list);
            dao.save(recipeEntity);
        } else throw new CheckVersionException("Такой версии не существует");
        checkUserAndSend("Обновлена запись в журнале рецептов");
    }

    private void checkIngredient(List<AddIngredientDTO> addIngredientDTOS) {
        addIngredientDTOS.forEach(s -> productService.get(s.getProduct()));
    }
    private void checkUserAndSend(String actions) {
        UserHolder userHolder = new UserHolder();
        UserDTO user = userHolder.getUser();
        sendAudit(user, user.getUuid(), actions);
    }

    private void sendAudit(UserDTO userDto, UUID uuid, String actions) {
        try {
            JSONObject user = new JSONObject();
            user.put("uuid", userDto.getUuid());
            user.put("mail", userDto.getMail());
            user.put("fio", userDto.getFio());
            user.put("role", userDto.getRole());
            JSONObject object = new JSONObject();
            object.put("user", user);
            object.put("text", actions);
            object.put("type", "USER");
            object.put("uuidService", uuid);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost/api/v1/audit"))
                    .setHeader("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(object.toString())).build();

            httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (JSONException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
