package mk.ukim.finki.wp.eimt_lab.web.controller;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.model.dto.*;
import mk.ukim.finki.wp.eimt_lab.service.application.UserApplicationService;
import mk.ukim.finki.wp.eimt_lab.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final UserApplicationService userApplicationService;


    public UserController(UserService userService, UserApplicationService userApplicationService) {
        this.userService = userService;
        this.userApplicationService = userApplicationService;
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{username}")
    public ResponseEntity<RegisterUserResponseDto> findByUsername(@PathVariable String username) {
        return userApplicationService
                .findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    public ResponseEntity<RegisterUserResponseDto> me(@AuthenticationPrincipal User user) {
        return userApplicationService
                .findByUsername(user.getUsername())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponseDto> register(@RequestBody RegisterUserRequestDto registerUserRequestDto) {
        return userApplicationService
                .register(registerUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseDto> login(@RequestBody LoginUserRequestDto loginUserRequestDto) {
        return userApplicationService
                .login(loginUserRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }




}
