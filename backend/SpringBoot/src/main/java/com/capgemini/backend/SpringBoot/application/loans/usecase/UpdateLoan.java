package com.capgemini.backend.SpringBoot.application.loans.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.prestamos.PrestamoNotFoundException;
import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateLoan {

    @Autowired
    private LoanRepository loanRepository;

    public Loan execute(Long id, Loan loan) {
        return loanRepository.findById(id)
                .map(existingLoan -> {
                    existingLoan.setNombreJuego(loan.getNombreJuego());
                    existingLoan.setNombreCliente(loan.getNombreCliente());
                    existingLoan.setFechaCreacion(loan.getFechaCreacion());
                    existingLoan.setFechaDevolucion(loan.getFechaDevolucion());
                    return loanRepository.save(existingLoan);
                })
                .orElseThrow(() -> new PrestamoNotFoundException("Préstamo no encontrado con ID: " + id));
    }
}