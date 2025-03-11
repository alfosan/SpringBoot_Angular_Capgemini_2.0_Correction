package com.capgemini.backend.SpringBoot.infraestructure.loans.repository;

import com.capgemini.backend.SpringBoot.domain.loans.model.Loan;
import com.capgemini.backend.SpringBoot.infraestructure.common.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoanSpecification {

    public static final String EQUALITY = ":";
    public static final String NEGATION = "!";
    public static final String GREATER_THAN = ">";
    public static final String LESS_THAN = "<";
    public static final String LIKE = "~";
    public static final String STARTS_WITH = "^";
    public static final String ENDS_WITH = "$";
    public static final String CONTAINS = "*";

    public Specification<Loan> buildSpecification(List<SearchCriteria> criteriaList) {
        return (root, query, criteriaBuilder) -> {
            Specification<Loan> specification = Specification.where(null);
            for (SearchCriteria criteria : criteriaList) {
                switch (criteria.getOperation()) {
                    case EQUALITY:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.equal(root1.get(criteria.getKey()), criteria.getValue()));
                        break;
                    case NEGATION:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.notEqual(root1.get(criteria.getKey()), criteria.getValue()));
                        break;
                    case GREATER_THAN:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.greaterThan(root1.get(criteria.getKey()), criteria.getValue().toString()));
                        break;
                    case LESS_THAN:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.lessThan(root1.get(criteria.getKey()), criteria.getValue().toString()));
                        break;
                    case LIKE:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                        break;
                    case STARTS_WITH:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get(criteria.getKey()), criteria.getValue() + "%"));
                        break;
                    case ENDS_WITH:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get(criteria.getKey()), "%" + criteria.getValue()));
                        break;
                    case CONTAINS:
                        specification = specification.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.like(root1.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                        break;
                }
            }
            return specification.toPredicate(root, query, criteriaBuilder);
        };
    }
}