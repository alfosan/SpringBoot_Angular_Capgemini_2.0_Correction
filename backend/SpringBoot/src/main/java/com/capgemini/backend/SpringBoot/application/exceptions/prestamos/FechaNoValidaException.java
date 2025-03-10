package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class FechaNoValidaException extends RuntimeException {
    public FechaNoValidaException(String message) {
        super(message);
    }
}