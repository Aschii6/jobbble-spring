package com.example.jobbble_spring.exceptions;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException(String message) {
        super(message);
    }
}
