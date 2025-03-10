package com.capgemini.backend.SpringBoot.application.clients.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.clients.ClienteNotFoundException;
import com.capgemini.backend.SpringBoot.domain.clients.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCliente {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean execute(Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return true;
                })
                .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
    }
}