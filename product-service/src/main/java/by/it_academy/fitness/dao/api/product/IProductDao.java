package by.it_academy.fitness.dao.api.product;

import by.it_academy.fitness.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IProductDao extends JpaRepository<ProductEntity, UUID> {
    ProductEntity findByTitle(String title);
}
