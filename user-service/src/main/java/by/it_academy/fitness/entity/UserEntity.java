package by.it_academy.fitness.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "user")
@SecondaryTable(
        schema = "fitness", name = "verification",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "uuid")
)
public class UserEntity {
    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "password")
    private String password;
    @Column(name = "dt_create")
    private Instant dtCreate;
    @Column(name = "dt_update")
    @Version
    private Instant dtUpdate;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "fitness",
            name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_uuid"),
            inverseJoinColumns =
            @JoinColumn(name = "role_id")
    )
    private RoleEntity role;
    @Enumerated(EnumType.STRING)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(schema = "fitness",
            name = "user_status",
            joinColumns =
            @JoinColumn(name = "user_uuid"),
            inverseJoinColumns =
            @JoinColumn(name = "status_id")
    )
    private StatusEntity status;
    @Column(name = "code", table = "verification")
    private String code;

    public UserEntity() {
    }

    public UserEntity( String mail, String fio, String password, Instant dtCreate, Instant dtUpdate, RoleEntity role, StatusEntity status) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.role = role;
        this.status = status;
    }

    public UserEntity(String mail, String fio, String password, Instant dtCreate, Instant dtUpdate, RoleEntity role, StatusEntity status, String code) {
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.role = role;
        this.status = status;
        this.code = code;
    }

    public UserEntity(UUID uuid, String mail, String fio, String password, Instant dtCreate, Instant dtUpdate, RoleEntity role, StatusEntity status, String code) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.password = password;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.role = role;
        this.status = status;
        this.code = code;
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

    public String getPassword() {
        return password;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public RoleEntity getRole() {
        return role;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDtUpdate(Instant dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}