package com.capgemini.backend.SpringBoot.infraestructure.loans.mapper;

import com.capgemini.backend.SpringBoot.application.dto.loans.LoanDTO;
import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;

public class LoanMapper {

    public static LoanDTO toDTO(Loan loan) {
        return new LoanDTO(loan.getId(), loan.getNombreJuego(), loan.getNombreCliente(), loan.getFechaCreacion(), loan.getFechaDevolucion());
    }

    public static Loan toEntity(LoanDTO loanDTO) {
        return new Loan(loanDTO.getId(), loanDTO.getNombreJuego(), loanDTO.getNombreCliente(), loanDTO.getFechaCreacion(), loanDTO.getFechaDevolucion());
    }
}