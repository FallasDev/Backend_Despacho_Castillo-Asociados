package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateServiceUseCase {

    private final ServiceRepository serviceRepository;

    public UpdateServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public DomainService execute(ServiceRequest service, int id) {

        if (service == null) {
            throw new BadRequestException("service.exception.update.cannot_be_null");
        }

        if (service.getName() == null || service.getName().isEmpty()) {
            throw new BadRequestException("service.exception.update.name.cannot_be_null");
        }

        Optional<DomainService> existingService = serviceRepository.findById(id);


        if (existingService.isEmpty()) {
            throw new BadRequestException("service.exception.update.notfound");
        }

        if (!existingService.get().isActive()) {
            throw new BadRequestException("service.exception.update.is_not_active");
        }

        DomainService updatedDomainService = serviceRepository.update(service, id);

        if (updatedDomainService == null) {
            throw new BadRequestException("service.exception.update.failed");
        }


        return updatedDomainService;
    }

}
