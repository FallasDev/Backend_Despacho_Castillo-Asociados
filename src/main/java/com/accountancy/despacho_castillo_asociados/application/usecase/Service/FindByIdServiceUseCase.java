package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;

public class FindByIdServiceUseCase  {

    private final ServiceRepository serviceRepository;

    public FindByIdServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public DomainService execute(int id) {
        DomainService domainService = serviceRepository.findById(id).orElse(null);

        if (domainService == null || !domainService.isActive()) {
            throw new EntityNotFoundException("services.exception.fetch.by_id.notfound");
        }

        return domainService;
    }

}
