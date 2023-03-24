package by.it_academy.fitness.config;

import by.it_academy.fitness.dao.api.product.IProductDao;
import by.it_academy.fitness.dao.api.product.IRecipeDao;
import by.it_academy.fitness.service.*;
import by.it_academy.fitness.service.api.product.IProductService;
import by.it_academy.fitness.service.api.product.IRecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class SpringConfig {

    @Bean
    public IProductService productService(IProductDao dao, ConversionService conversionService ) {
        return new ProductService(dao, conversionService);
    }

    @Bean
    public IRecipeService recipeService(IRecipeDao dao, IProductService productService, ConversionService conversionService) {
        return new RecipeService(dao, productService, conversionService);
    }
}
