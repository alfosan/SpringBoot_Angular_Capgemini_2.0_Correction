package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class JuegoNoReconocidoException extends RuntimeException {
    public JuegoNoReconocidoException(String message) {
        super(message);
    }
}