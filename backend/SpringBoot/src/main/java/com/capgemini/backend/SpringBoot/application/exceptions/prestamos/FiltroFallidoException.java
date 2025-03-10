package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class FiltroFallidoException extends RuntimeException {
    public FiltroFallidoException(String message) {
        super(message);
    }
}