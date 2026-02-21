package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> register(@RequestBody User user) {
        String token = authService.register(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Registration successful");
        return response;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> login(@RequestBody User user) {
        String token = authService.login(user);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Login successful");
        return response;
    }
}
