package by.it_academy.fitness.entity;

import by.it_academy.fitness.userEnum.EntityType;
import jakarta.persistence.*;

@Entity
@Table(schema = "fitness", name = "type")
public class TypeEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private EntityType type;

    public TypeEntity(EntityType type) {
        this.type = type;
    }

    public TypeEntity() {
    }

    public EntityType getType() {
        return type;
    }
}