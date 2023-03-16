package by.it_academy.fitness.core.converter.product;

import by.it_academy.fitness.core.dto.product.ProductDTO;
import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityToProductDTO implements Converter<ProductEntity, ProductDTO> {
    @Override
    public ProductDTO convert(ProductEntity productEntity) {
        return new ProductDTO(
                productEntity.getUuid(),
                productEntity.getDtCreate(),
                productEntity.getDtUpdate(),
                productEntity.getTitle(),
                productEntity.getWeight(),
                productEntity.getCalories(),
                productEntity.getProteins(),
                productEntity.getFats(),
                productEntity.getCarbohydrates());
    }
}
