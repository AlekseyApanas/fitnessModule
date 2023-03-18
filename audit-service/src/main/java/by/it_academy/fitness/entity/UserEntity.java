package by.it_academy.fitness.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "user")
public class UserEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(schema = "fitness",
            name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_uuid"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id")
    )

    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String mail, String fio, RoleEntity role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public RoleEntity getRole() {
        return role;
    }
}