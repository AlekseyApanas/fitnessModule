package by.it_academy.fitness.service.api.product;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.report.AddProductDTO;
import by.it_academy.fitness.core.dto.report.ProductDTO;
import by.it_academy.fitness.core.dto.report.UpdateProductDTO;

import java.util.UUID;


public interface IProductService<T> {
    UUID create(AddProductDTO productDTO);

    PageDTO<T> get(int page, int size);

    UUID update(UpdateProductDTO productDTO);

    ProductDTO get(UUID id);
}
