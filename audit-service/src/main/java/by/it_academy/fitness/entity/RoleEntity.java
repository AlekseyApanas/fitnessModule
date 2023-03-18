package by.it_academy.fitness.entity;

import by.it_academy.fitness.userEnum.UserRole;
import jakarta.persistence.*;


@Entity
@Table(schema = "fitness", name = "role")
public class RoleEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public RoleEntity() {
    }

    public RoleEntity(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }
}

