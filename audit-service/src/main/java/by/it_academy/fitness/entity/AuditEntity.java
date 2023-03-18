package by.it_academy.fitness.entity;

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
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(
            name = "user_uuid"
    )
    private UserEntity userEntity;
    @Column(name = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(schema = "fitness",
            name = "user_type",
            joinColumns =
            @JoinColumn(name = "audit_uuid"),
            inverseJoinColumns =
            @JoinColumn(name = "type_id")
    )
    private TypeEntity type;
    @Column(name = "uuidService")
    private String uuidService;

    public AuditEntity() {
    }

    public AuditEntity(Instant dtCreate, UserEntity userEntity, String text, TypeEntity type, String uuidService) {

        this.dtCreate = dtCreate;
        this.userEntity = userEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public String getText() {
        return text;
    }

    public TypeEntity getType() {
        return type;
    }

    public String getUuidService() {
        return uuidService;
    }
}
