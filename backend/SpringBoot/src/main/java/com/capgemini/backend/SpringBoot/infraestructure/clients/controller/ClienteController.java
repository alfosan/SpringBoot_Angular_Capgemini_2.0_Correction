package com.capgemini.backend.SpringBoot.infraestructure.clients.controller;

import com.capgemini.backend.SpringBoot.application.clients.service.ClienteService;
import com.capgemini.backend.SpringBoot.domain.clients.model.dto.ClienteDto;
import com.capgemini.backend.SpringBoot.infrastructure.clients.mapper.ClienteMapper;
import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public List<ClienteDto> getAllClientes() {
        return clienteService.getAllClientes().stream()
                .map(ClienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return cliente.map(c -> ResponseEntity.ok(ClienteMapper.toDTO(c)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo cliente
    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente nuevoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteMapper.toDTO(nuevoCliente));
    }

    // Actualizar un cliente existente
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDTO) {
        Cliente cliente = ClienteMapper.toEntity(clienteDTO);
        Cliente clienteActualizado = clienteService.updateCliente(id, cliente);
        return ResponseEntity.ok(ClienteMapper.toDTO(clienteActualizado));
    }

    // Eliminar un cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}