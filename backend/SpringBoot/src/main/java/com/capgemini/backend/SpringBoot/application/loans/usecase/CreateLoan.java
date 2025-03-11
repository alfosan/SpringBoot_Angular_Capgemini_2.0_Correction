package com.capgemini.backend.SpringBoot.application.loans.usecase;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLoan {

    @Autowired
    private LoanRepository loanRepository;

    public Loan execute(Loan loan) {
        return loanRepository.save(loan);
    }
}