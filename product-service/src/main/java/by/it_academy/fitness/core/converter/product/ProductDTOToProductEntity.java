package by.it_academy.fitness.core.converter.product;

import by.it_academy.fitness.core.dto.product.ProductDTO;
import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductDTOToProductEntity implements Converter<ProductDTO, ProductEntity> {

    @Override
    public ProductEntity convert(ProductDTO productDTO) {
        return new ProductEntity(
                productDTO.getUuid(),
                productDTO.getDtCreate(),
                productDTO.getDtUpdate(),
                productDTO.getTitle(),
                productDTO.getWeight(),
                productDTO.getCalories(),
                productDTO.getProteins(),
                productDTO.getFats(),
                productDTO.getCarbohydrates());
    }
}
