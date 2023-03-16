package by.it_academy.fitness.entity;

import by.it_academy.fitness.userEnum.UserStatus;
import jakarta.persistence.*;

@Entity
@Table(schema = "fitness",name = "status")
public class StatusEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public StatusEntity() {
    }

    public StatusEntity(UserStatus status) {
        this.status = status;
    }


    public UserStatus getStatus() {
        return status;
    }
}