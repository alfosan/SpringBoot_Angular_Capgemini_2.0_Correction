package com.capgemini.backend.SpringBoot.domain.clients.repository;

import com.capgemini.backend.SpringBoot.domain.clients.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByNombre(String nombre);
}