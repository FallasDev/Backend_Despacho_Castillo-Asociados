package com.accountancy.despacho_castillo_asociados.domain.repository.Service;



import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository {

    DomainService create(ServiceRequest serviceRequest);
    DomainService update(ServiceRequest serviceRequest, int id);
    boolean deactivate(int id);
    void activate(int id);

    Optional<DomainService> findById(int id);
    Optional<DomainService> findByName(String name);
    PageResult<DomainService> findByContainsNameLetterUseCase(String name, int page, int size);
    Optional<DomainService> findByNameAndIsActive(String name);
    Optional<DomainService> findByNameAndIsInactive(String name);
    PageResult<DomainService> findAll(int page, int size);

    boolean existsByNameAndIsActive(String name);

    boolean existsByNameAndIsInactive(String name);
}
