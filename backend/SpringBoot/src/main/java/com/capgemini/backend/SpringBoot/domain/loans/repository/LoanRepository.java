package com.capgemini.backend.SpringBoot.domain.loans.repository;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>, JpaSpecificationExecutor<Loan> {
    Page<Loan> findByNombreJuegoContainingAndNombreClienteContaining(String nombreJuego, String nombreCliente, Pageable pageable);
    Page<Loan> findByFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);
    Page<Loan> findByNombreJuegoContainingAndNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreJuego, String nombreCliente, LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

    List<Loan> findByNombreJuegoContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreJuego, LocalDate fechaFin, LocalDate fechaInicio);

    List<Loan> findByNombreClienteContainingAndFechaCreacionLessThanEqualAndFechaDevolucionGreaterThanEqual(
            String nombreCliente, LocalDate fechaFin, LocalDate fechaInicio);
}