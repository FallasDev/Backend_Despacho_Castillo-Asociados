package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class FormalitieJpaSpecificationBuilder {

    public Specification<FormalitieEntity> build(SearchFormalitie searchFormalitie) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchFormalitie.getClientId() != null && searchFormalitie.getClientId() > 0) {
                predicates.add(cb.equal(root.get("client").get("id"), searchFormalitie.getClientId()));
            }

            if (searchFormalitie.getClientName() != null && !searchFormalitie.getClientName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("client").get("name")), "%" + searchFormalitie.getClientName().toLowerCase() + "%"));
            }

            if (searchFormalitie.getServiceName() != null && !searchFormalitie.getServiceName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("service").get("name")), "%" + searchFormalitie.getServiceName().toLowerCase() + "%"));
            }

            if (searchFormalitie.getStateId() != null && searchFormalitie.getStateId() > 0) {
                predicates.add(cb.equal(root.get("state"), searchFormalitie.getStateId()));
            }

            if (searchFormalitie.getUserName() != null && !searchFormalitie.getUserName().isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("user").get("username")), "%" + searchFormalitie.getUserName().toLowerCase() + "%"));
            }

            if (searchFormalitie.getStartDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), searchFormalitie.getStartDate()));
            }

            if (searchFormalitie.getEndDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), searchFormalitie.getEndDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    }

}
