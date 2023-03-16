package by.it_academy.fitness.service.api.product;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.product.AddProductDTO;
import by.it_academy.fitness.core.dto.product.ProductDTO;
import by.it_academy.fitness.core.dto.product.UpdateProductDTO;

import java.util.UUID;


public interface IProductService<T> {
    void create(AddProductDTO productDTO);

    PageDTO<T> get(int page, int size);

    void update(UpdateProductDTO productDTO);
    ProductDTO get(UUID id);
}
