package by.it_academy.fitness.service;

import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.core.dto.product.AddProductDTO;
import by.it_academy.fitness.core.dto.product.ProductDTO;
import by.it_academy.fitness.core.dto.product.UpdateProductDTO;
import by.it_academy.fitness.core.exception.CheckDoubleException;
import by.it_academy.fitness.core.exception.CheckVersionException;
import by.it_academy.fitness.core.exception.NotFoundException;
import by.it_academy.fitness.dao.api.product.IProductDao;
import by.it_academy.fitness.entity.ProductEntity;
import by.it_academy.fitness.service.api.product.IProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private final IProductDao dao;

    private final ConversionService conversionService;

    public ProductService(IProductDao dao, ConversionService conversionService) {
        this.dao = dao;
        this.conversionService = conversionService;
    }

    @Override
    public void create(AddProductDTO productDTO) {
        ProductEntity productEntity = dao.findByTitle(productDTO.getTitle());
        if (productEntity != null) {
            throw new CheckDoubleException("Продукт с таким названием уже существует");
        } else {
            dao.save(conversionService.convert(productDTO, ProductEntity.class));
        }
    }

    @Override
    public PageDTO<ProductDTO> get(int page, int size) {
        PageRequest paging = PageRequest.of(page, size);
        Page<ProductEntity> all = dao.findAll(paging);
        List<ProductDTO> productsPages = all.getContent().stream()
                .map(s -> conversionService.convert(s, ProductDTO.class))
                .collect(Collectors.toList());
        return new PageDTO<>(page, size, all.getTotalPages(), all.getTotalElements(), all.isFirst(), all.getNumberOfElements(), all.isLast(), productsPages);
    }

    @Override
    public void update(UpdateProductDTO productDTO) {
        ProductEntity productEntity = dao.findById(productDTO.getUuid()).orElseThrow(() -> new NotFoundException("Такого продукта не существует"));
        if (productEntity.getDtUpdate().toEpochMilli() == productDTO.getDtUpdate().toEpochMilli()) {
            productEntity.setTitle(productDTO.getAddProductDTO().getTitle());
            productEntity.setWeight(productDTO.getAddProductDTO().getWeight());
            productEntity.setCalories(productDTO.getAddProductDTO().getCalories());
            productEntity.setProteins(productDTO.getAddProductDTO().getProteins());
            productEntity.setFats(productDTO.getAddProductDTO().getFats());
            productEntity.setCarbohydrates(productDTO.getAddProductDTO().getCarbohydrates());
            dao.save(productEntity);
        } else throw new CheckVersionException("Такой версии не существует");
    }

    @Override
    public ProductDTO get(UUID id) {
        ProductEntity productEntity = this.dao.findById(id).orElseThrow(() -> new NotFoundException("Такого продукта не существует"));
        return conversionService.convert(productEntity, ProductDTO.class);
    }
}