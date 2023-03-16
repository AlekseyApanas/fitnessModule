package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.recipe.AddRecipeDTO;
import by.it_academy.fitness.core.dto.recipe.UpdateRecipeDTO;
import by.it_academy.fitness.service.api.product.IRecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.Instant;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private IRecipeService iRecipeService;

    public RecipeController(IRecipeService iRecipeService) {
        this.iRecipeService = iRecipeService;
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID userUUID,
                                    @PathVariable("dt_update") Instant dtUpdate,
                                    @RequestBody @Valid AddRecipeDTO addRecipeDTO) {
        iRecipeService.update(new UpdateRecipeDTO(addRecipeDTO, dtUpdate, userUUID));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid AddRecipeDTO addRecipeDTO) {
        iRecipeService.create(addRecipeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> get(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "20") @Min(0) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(iRecipeService.get(page, size));
    }
}

