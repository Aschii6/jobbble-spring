package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.exceptions.InvalidLoginException;
import com.example.jobbble_spring.exceptions.MisformattedEmailException;
import com.example.jobbble_spring.exceptions.TakenUsernameException;
import com.example.jobbble_spring.exceptions.WeakPasswordException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(MisformattedEmailException.class)
    public ResponseEntity<String> handleMisformattedEmail(MisformattedEmailException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<String> handleWeakPassword(WeakPasswordException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }

    @ExceptionHandler(TakenUsernameException.class)
    public ResponseEntity<String> handleTakenUsername(TakenUsernameException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<String> handleInvalidLogin(InvalidLoginException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            errorMessage.append(violation.getMessage()).append(". ");
        }
        if (!errorMessage.isEmpty()) {
            errorMessage.setLength(errorMessage.length() - 2);
        }
        return ResponseEntity.unprocessableContent().body(errorMessage.toString());
    }
}
