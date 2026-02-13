package com.example.jobbble_spring.utils;

import com.example.jobbble_spring.entities.User;
import com.example.jobbble_spring.repositories.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserUtils {
    @Autowired
    private UserRepository userRepository;

    public @NonNull User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated.");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (userDetails == null) {
            throw new RuntimeException("User details not found.");
        }
        return userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
