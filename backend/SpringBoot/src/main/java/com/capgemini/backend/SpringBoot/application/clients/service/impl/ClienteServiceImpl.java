package com.capgemini.backend.SpringBoot.application.clients.service.impl;

import com.capgemini.backend.SpringBoot.application.clients.service.ClienteService;
import com.capgemini.backend.SpringBoot.application.clients.usecase.*;
import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private GetAllClientes getAllClientes;

    @Autowired
    private GetClienteById getClienteById;

    @Autowired
    private CreateCliente createCliente;

    @Autowired
    private UpdateCliente updateCliente;

    @Autowired
    private DeleteCliente deleteCliente;

    /**
     * Obtiene todos los clientes almacenados en la base de datos
     */
    @Override
    public List<Cliente> getAllClientes() {
        return getAllClientes.execute();
    }

    /**
     * Busca un cliente por su ID
     */
    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return getClienteById.execute(id);
    }

    /**
     * Crea un nuevo cliente en la base de datos
     */
    @Override
    public Cliente createCliente(Cliente cliente) {
        return createCliente.execute(cliente);
    }

    /**
     * Actualiza los datos de un cliente existente
     */
    @Override
    public Cliente updateCliente(Long id, Cliente cliente) {
        return updateCliente.execute(id, cliente);
    }

    /**
     * Elimina un cliente de la base de datos
     */
    @Override
    public boolean deleteCliente(Long id) {
        return deleteCliente.execute(id);
    }
}