package com.capgemini.backend.SpringBoot.application.loans.service;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    List<Loan> getAllLoans();
    Optional<Loan> getLoanById(Long id);
    Loan createLoan(Loan loan);
    Loan updateLoan(Long id, Loan loan);
    boolean deleteLoan(Long id);
    Page<Loan> findByFilters(String nombreJuego, String nombreCliente, LocalDate fecha, Pageable pageable);
}