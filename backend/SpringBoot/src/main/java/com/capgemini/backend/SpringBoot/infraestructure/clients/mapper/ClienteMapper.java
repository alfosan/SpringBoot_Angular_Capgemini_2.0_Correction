package com.capgemini.backend.SpringBoot.infrastructure.clients.mapper;

import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import com.capgemini.backend.SpringBoot.domain.clients.model.dto.ClienteDto;

public class ClienteMapper {

    public static ClienteDto toDTO(Cliente cliente) {
        return new ClienteDto(cliente.getId(), cliente.getNombre());
    }

    public static Cliente toEntity(ClienteDto clienteDTO) {
        return new Cliente(clienteDTO.getId(), clienteDTO.getNombre());
    }
}