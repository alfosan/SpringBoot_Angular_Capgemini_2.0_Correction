package com.capgemini.backend.SpringBoot.application.loans.usecase;

import com.capgemini.backend.SpringBoot.application.exceptions.prestamos.PrestamoNotFoundException;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteLoan {

    @Autowired
    private LoanRepository loanRepository;

    public boolean execute(Long id) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loanRepository.delete(loan);
                    return true;
                })
                .orElseThrow(() -> new PrestamoNotFoundException("Préstamo no encontrado con ID: " + id));
    }
}