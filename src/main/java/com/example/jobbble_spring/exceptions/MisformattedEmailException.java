package com.example.jobbble_spring.exceptions;

public class MisformattedEmailException extends RuntimeException {
    public MisformattedEmailException(String message) {
        super(message);
    }
}
