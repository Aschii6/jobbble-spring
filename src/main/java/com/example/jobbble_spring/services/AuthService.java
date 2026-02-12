package com.example.jobbble_spring.services;

import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.exceptions.InvalidLoginException;
import com.example.jobbble_spring.exceptions.MisformattedEmailException;
import com.example.jobbble_spring.exceptions.TakenUsernameException;
import com.example.jobbble_spring.exceptions.WeakPasswordException;
import com.example.jobbble_spring.repositories.UserRepository;
import com.example.jobbble_spring.security.JwtService;
import com.example.jobbble_spring.validators.UserValidator;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidator userValidator;

    public String register(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new TakenUsernameException("Username is already taken.");
        }

        Errors errors = new BeanPropertyBindingResult(user, "user");
        userValidator.validate(user, errors);

        if (errors.hasErrors()) {
            ObjectError oe = errors.getAllErrors().getFirst();
            if (Objects.equals(oe.getCode(), "weak.password")) {
                throw new WeakPasswordException(oe.getDefaultMessage());
            }
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return jwtService.generateToken(savedUser);
    }

    public String login(User user) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (auth.isAuthenticated()) {
            User loggedInUser = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new InvalidLoginException("Invalid credentials."));
            return jwtService.generateToken(loggedInUser);
        } else {
            throw new InvalidLoginException("Invalid credentials.");
        }
    }
}
