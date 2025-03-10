package com.capgemini.backend.SpringBoot.application.exceptions.games;

public class DuplicateGameTitleException extends RuntimeException {
    public DuplicateGameTitleException(String message) {
        super(message);
    }
}