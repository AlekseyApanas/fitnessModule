package by.it_academy.fitness.web.controllers;

import by.it_academy.fitness.core.dto.user.UserDTO;
import by.it_academy.fitness.core.dto.user.UserLogInDTO;
import by.it_academy.fitness.core.dto.user.UserRegistrationDTO;
import by.it_academy.fitness.service.UserHolder;
import by.it_academy.fitness.service.api.user.IAuthenticationService;
import by.it_academy.fitness.web.utils.JwtTokenUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/api/v1/users")
public class AuthenticationController {
    private IAuthenticationService service;
    private UserHolder userHolder;

    public AuthenticationController(IAuthenticationService service, UserHolder userHolder) {
        this.service = service;
        this.userHolder = userHolder;
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
        service.registration(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(path = "/verification", method = RequestMethod.GET)
    public ResponseEntity<?> verification(@RequestParam("code") @NotBlank String code, @RequestParam("mail") @Email String mail) {
        service.verification(code, mail);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> logIn(@RequestBody @Valid UserLogInDTO userLogInDTO) {
       UserDTO userDTO= service.logIn(userLogInDTO);
        return ResponseEntity.status(HttpStatus.OK).body(JwtTokenUtil.generateToken(userDTO));
    }

    @RequestMapping(path = "/me", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> get() {
        return ResponseEntity.status(HttpStatus.OK).body(userHolder.getUser());
    }
}
