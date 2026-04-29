package mk.ukim.finki.wp.eimt_lab.web.controller;

import mk.ukim.finki.wp.eimt_lab.model.domain.User;
import mk.ukim.finki.wp.eimt_lab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.wp.eimt_lab.service.domain.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

}
