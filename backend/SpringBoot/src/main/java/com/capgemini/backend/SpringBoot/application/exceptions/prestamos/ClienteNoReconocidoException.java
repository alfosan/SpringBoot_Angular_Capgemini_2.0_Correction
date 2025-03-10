package com.capgemini.backend.SpringBoot.application.exceptions.prestamos;

public class ClienteNoReconocidoException extends RuntimeException {
    public ClienteNoReconocidoException(String message) {
        super(message);
    }
}