package by.it_academy.fitness.core.dto.user;


import by.it_academy.fitness.userEnum.UserRole;
import by.it_academy.fitness.userEnum.UserStatus;

import java.time.Instant;

public class SavedUserDTO {

    private AddUserDTO userDTO;
    private Instant dtUpdate;
    private Instant dtCreate;
    private UserRegistrationDTO userRegistrationDTO;
    private UserRole role;

    private UserStatus status;

    public SavedUserDTO(AddUserDTO userDTO) {
        this.userDTO = userDTO;
        this.dtCreate = Instant.now();
        this.dtUpdate = this.dtCreate;
    }

    public SavedUserDTO(UserRegistrationDTO userRegistrationDTO) {
        this.userRegistrationDTO = userRegistrationDTO;
        this.dtCreate = Instant.now();
        this.dtUpdate = this.dtCreate;
        this.role = UserRole.USER;
        this.status = UserStatus.WAITING_ACTIVATION;
    }

    public AddUserDTO getUserDTO() {
        return userDTO;
    }

    public Instant getDtUpdate() {
        return dtUpdate;
    }

    public Instant getDtCreate() {
        return dtCreate;
    }

    public UserRegistrationDTO getUserRegistrationDTO() {
        return userRegistrationDTO;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}
