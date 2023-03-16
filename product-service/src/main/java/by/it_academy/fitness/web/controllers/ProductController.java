package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.product.AddProductDTO;
import by.it_academy.fitness.core.dto.product.UpdateProductDTO;
import by.it_academy.fitness.service.api.product.IProductService;
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
@RequestMapping("/api/v1/product")
public class ProductController {
    private IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID userUUID,
                                    @PathVariable("dt_update") Instant dtUpdate,
                                    @RequestBody @Valid AddProductDTO addProductDTO) {
        iProductService.update(new UpdateProductDTO(addProductDTO, dtUpdate, userUUID));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid AddProductDTO addProductDTO) {
        iProductService.create(addProductDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> get(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "20") @Min(0) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(iProductService.get(page, size));
    }
}
