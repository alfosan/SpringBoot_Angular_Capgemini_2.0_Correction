package com.capgemini.backend.SpringBoot.application.clients.usecase;

import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import com.capgemini.backend.SpringBoot.domain.clients.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetClienteById {

    @Autowired
    private ClienteRepository clienteRepository;

    public Optional<Cliente> execute(Long id) {
        return clienteRepository.findById(id);
    }
}