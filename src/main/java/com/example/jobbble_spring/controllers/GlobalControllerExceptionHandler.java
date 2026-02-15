package com.example.jobbble_spring.controllers;

import com.example.jobbble_spring.exceptions.InvalidLoginException;
import com.example.jobbble_spring.exceptions.MisformattedEmailException;
import com.example.jobbble_spring.exceptions.TakenUsernameException;
import com.example.jobbble_spring.exceptions.WeakPasswordException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(MisformattedEmailException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public String handleMisformattedEmail(MisformattedEmailException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(WeakPasswordException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public String handleWeakPassword(WeakPasswordException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(TakenUsernameException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTakenUsername(TakenUsernameException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidLogin(InvalidLoginException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public String handleConstraintViolation(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            errorMessage.append(violation.getMessage()).append(". ");
        }
        if (!errorMessage.isEmpty()) {
            errorMessage.setLength(errorMessage.length() - 2);
        }
        return errorMessage.toString();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntimeException(RuntimeException ex) {
        return "An unexpected error occurred: " + ex.getMessage();
    }
}
