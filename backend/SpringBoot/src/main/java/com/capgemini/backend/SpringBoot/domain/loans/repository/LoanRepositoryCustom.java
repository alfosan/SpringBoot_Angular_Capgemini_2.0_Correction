package com.capgemini.backend.SpringBoot.domain.loans.repository;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.infraestructure.common.criteria.SearchCriteria;

import java.util.List;

public interface LoanRepositoryCustom {
    List<Loan> findLoansByCriteria(List<SearchCriteria> criteriaList);
}