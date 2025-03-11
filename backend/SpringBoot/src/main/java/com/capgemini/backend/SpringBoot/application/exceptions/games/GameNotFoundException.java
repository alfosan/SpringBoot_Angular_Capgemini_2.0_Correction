package com.capgemini.backend.SpringBoot.application.exceptions.games;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message) {
        super(message);
    }
}
