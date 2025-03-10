package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class PrestamoMaximoException extends RuntimeException {
    public PrestamoMaximoException(String message) {
        super(message);
    }
}