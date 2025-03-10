package com.capgemini.backend.SpringBoot.application.exceptions.clients;

public class DuplicateClientNameException extends RuntimeException {
    public DuplicateClientNameException(String message) {
        super(message);
    }
}
