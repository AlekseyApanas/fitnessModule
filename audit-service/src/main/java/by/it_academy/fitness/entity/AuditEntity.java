package by.it_academy.fitness.entity;

import by.it_academy.fitness.userEnum.EntityType;
import by.it_academy.fitness.userEnum.UserRole;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "audit")
public class AuditEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "dt_create")
    private Instant dtCreate;

    @Column(name = "uuidUser")
    private UUID uuidUser;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "entity")
    private UserRole role;
    @Column(name = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EntityType type;
    @Column(name = "uuidService")
    private String uuidService;

    public AuditEntity(UUID uuid, Instant dtCreate, UUID uuidUser, String mail, String fio, UserRole role, String text, EntityType type, String uuidService) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.uuidUser = uuidUser;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public AuditEntity() {
    }

    public AuditEntity(Instant dtCreate, UUID uuidUser, String mail, String fio, UserRole role, String text, EntityType type, String uuidService) {
        this.dtCreate = dtCreate;
        this.uuidUser = uuidUser;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public UUID getUuidUser() {
        return uuidUser;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getRole() {
        return role;
    }

    public String getText() {
        return text;
    }

    public EntityType getType() {
        return type;
    }

    public String getUuidService() {
        return uuidService;
    }
}