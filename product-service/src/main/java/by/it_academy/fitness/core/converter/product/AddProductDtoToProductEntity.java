package by.it_academy.fitness.core.converter.product;

import by.it_academy.fitness.core.dto.product.AddProductDTO;
import by.it_academy.fitness.core.dto.product.SavedProductDTO;
import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddProductDtoToProductEntity implements Converter<AddProductDTO, ProductEntity> {
    @Override
    public ProductEntity convert(AddProductDTO addProductDTO) {
        SavedProductDTO savedProductDTO = new SavedProductDTO(addProductDTO);
        return new ProductEntity(
                savedProductDTO.getDtCreate(),
                savedProductDTO.getDtUpdate(),
                savedProductDTO.getAddProductDTO().getTitle(),
                savedProductDTO.getAddProductDTO().getWeight(),
                savedProductDTO.getAddProductDTO().getCalories(),
                savedProductDTO.getAddProductDTO().getProteins(),
                savedProductDTO.getAddProductDTO().getFats(),
                savedProductDTO.getAddProductDTO().getCarbohydrates());
    }
}
