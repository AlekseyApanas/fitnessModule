package by.it_academy.fitness.dao.api.product;

import by.it_academy.fitness.entity.RecipeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IRecipeDao extends JpaRepository<RecipeEntity, UUID> {

    RecipeEntity findByTitle(String title);
}
