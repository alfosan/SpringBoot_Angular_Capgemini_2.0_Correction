package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class PrestamoNotFoundException extends RuntimeException {
    public PrestamoNotFoundException(String message) {
        super(message);
    }
}