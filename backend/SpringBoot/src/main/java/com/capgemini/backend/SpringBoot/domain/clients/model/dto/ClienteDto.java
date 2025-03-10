package com.capgemini.backend.SpringBoot.domain.clients.model.dto;

public class ClienteDto {
    private Long id;
    private String nombre;

    // Constructor
    public ClienteDto(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}