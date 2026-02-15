package com.example.jobbble_spring.exceptions;

public class TakenUsernameException extends RuntimeException {
    public TakenUsernameException(String message) {
        super(message);
    }
}
