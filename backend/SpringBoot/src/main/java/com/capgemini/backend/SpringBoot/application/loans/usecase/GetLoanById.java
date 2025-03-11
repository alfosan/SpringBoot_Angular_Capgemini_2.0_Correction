package com.capgemini.backend.SpringBoot.application.loans.usecase;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetLoanById {

    @Autowired
    private LoanRepository loanRepository;

    public Optional<Loan> execute(Long id) {
        return loanRepository.findById(id);
    }
}