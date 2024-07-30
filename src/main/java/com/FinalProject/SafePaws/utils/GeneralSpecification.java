package com.FinalProject.SafePaws.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;

public class GeneralSpecification<T, D> {
    public static <T, D> Specification<T> getSpecification(D searchDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (var field : searchDTO.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(searchDTO);
                    if (value != null) {
                        if (value instanceof String && !((String) value).isEmpty()) {
                            predicates.add(criteriaBuilder.like(root.get(field.getName()), "%" + value + "%"));
                        } else if (!(value instanceof String)) {
                            predicates.add(criteriaBuilder.equal(root.get(field.getName()), value));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); 
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
