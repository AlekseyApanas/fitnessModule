package by.it_academy.fitness.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class IngredientEntity {
    @ManyToOne
    @JoinColumn(
            name = "product_uuid"
    )
    private ProductEntity product;
    @Column(name = "weight")
    private Integer weight;

    public IngredientEntity(ProductEntity product, Integer weight) {
        this.product = product;
        this.weight = weight;
    }

    public IngredientEntity() {
    }

    public ProductEntity getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }
}
