package by.it_academy.fitness.web.controllers;


import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.core.dto.user.UpdateUserDTO;
import by.it_academy.fitness.core.dto.user.AddUserDTO;
import by.it_academy.fitness.core.dto.page.PageDTO;
import by.it_academy.fitness.service.api.user.IUserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.time.Instant;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;


    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<UserDTO> get(@PathVariable("uuid") UUID userUUID) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(userUUID));
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> update(@PathVariable("uuid") UUID userUUID,
                                    @PathVariable("dt_update") Instant dtUpdate,
                                    @RequestBody @Valid AddUserDTO userDTO) {
        userService.update(new UpdateUserDTO(userDTO, dtUpdate, userUUID));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AddUserDTO usersDTO) {
        userService.create(usersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageDTO> get(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "20") @Min(0) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.get(page, size));
    }
}
