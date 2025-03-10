package com.capgemini.backend.SpringBoot.application.clients.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.clients.ClienteNotFoundException;
import com.capgemini.backend.SpringBoot.application.exceptions.clients.DuplicateClientNameException;
import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import com.capgemini.backend.SpringBoot.domain.clients.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente execute(Long id, Cliente cliente) {
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    if (!existingCliente.getNombre().equals(cliente.getNombre()) && clienteRepository.existsByNombre(cliente.getNombre())) {
                        throw new DuplicateClientNameException("Ya existe un cliente con el nombre: " + cliente.getNombre());
                    }
                    existingCliente.setNombre(cliente.getNombre());
                    return clienteRepository.save(existingCliente);
                })
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
    }
}