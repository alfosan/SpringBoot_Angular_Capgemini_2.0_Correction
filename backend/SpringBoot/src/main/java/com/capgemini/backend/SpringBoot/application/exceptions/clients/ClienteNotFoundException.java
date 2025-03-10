package com.capgemini.backend.SpringBoot.application.exceptions.clients;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String message) {
        super(message);
    }
}
