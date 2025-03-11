package com.capgemini.backend.SpringBoot.infraestructure.loans.repository;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.domain.loans.repository.LoanRepositoryCustom;
import com.capgemini.backend.SpringBoot.infraestructure.common.criteria.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;

@Repository
public class LoanRepositoryImpl implements LoanRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Loan> findLoansByCriteria(List<SearchCriteria> criteriaList) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Loan> criteriaQuery = criteriaBuilder.createQuery(Loan.class);
        Root<Loan> root = criteriaQuery.from(Loan.class);

        Specification<Loan> specification = Specification.where(null);
        for (SearchCriteria criteria : criteriaList) {
            specification = specification.and(new LoanSpecification(criteria));
        }
        Predicate predicate = specification.toPredicate(root, criteriaQuery, criteriaBuilder);

        criteriaQuery.where(predicate);

        TypedQuery<Loan> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}