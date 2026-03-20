package com.shivam.databaseinitapplication.controllers;

import com.shivam.databaseinitapplication.entity.User;
import com.shivam.databaseinitapplication.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}