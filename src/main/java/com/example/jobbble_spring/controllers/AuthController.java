package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        try {
            return authService.register(user);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        try {
            return authService.login(user);
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }
}
