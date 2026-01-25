package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;

public class FindByIdServiceUseCase  {

    private final ServiceRepository serviceRepository;
    private final Messages messages;

    public FindByIdServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

    public DomainService execute(int id) {
        DomainService domainService = serviceRepository.findById(id).orElse(null);

        if (domainService == null || !domainService.isActive()) {
            throw new EntityNotFoundException(messages.get("service.exception.fetch.by_id.notfound", new Object[]{id}));
        }

        return domainService;
    }

}
