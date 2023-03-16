package by.it_academy.fitness.entity;

import by.it_academy.fitness.core.converter.InstantToLongConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "product")
public class ProductEntity {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @JsonSerialize(converter = InstantToLongConverter.Serializer.class)
    @Column(name = "dt_create")
    private Instant dtCreate;
    @JsonSerialize(converter = InstantToLongConverter.Serializer.class)

    @Column(name = "dt_update")
    @Version
    private Instant dtUpdate;
    @Column(name = "title")
    private String title;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "calories")
    private Integer calories;
    @Column(name = "proteins")
    private Double proteins;
    @Column(name = "fats")
    private Double fats;
    @Column(name = "carbohydrates")
    private Double carbohydrates;

    public ProductEntity() {
    }

    public ProductEntity(UUID uuid, Instant dtCreate, Instant dtUpdate, String title, Integer weight, Integer calories, double proteins, double fats, double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public ProductEntity(Instant dtCreate, Instant dtUpdate, String title, Integer weight, Integer calories, Double proteins, Double fats, Double carbohydrates) {
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCalories() {
        return calories;
    }

    public Double getProteins() {
        return proteins;
    }

    public Double getFats() {
        return fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setDtCreate(Instant dtCreate) {
        this.dtCreate = dtCreate;
    }

    public void setDtUpdate(Instant dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }
}
