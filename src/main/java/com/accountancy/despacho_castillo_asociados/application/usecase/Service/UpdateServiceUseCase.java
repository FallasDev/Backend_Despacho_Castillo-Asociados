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
            throw new BadRequestException("Service cannot be null");
        }

        if (service.getName() == null || service.getName().isEmpty()) {
            throw new BadRequestException("Service name cannot be null or empty");
        }

        Optional<DomainService> existingService = serviceRepository.findById(id);


        if (existingService.isEmpty()) {
            throw new BadRequestException("Service with id " + id + " does not exist");
        }

        if (!existingService.get().isActive()) {
            throw new BadRequestException("Service with id " + id + " is not active");
        }

        DomainService updatedDomainService = serviceRepository.update(service, id);

        if (updatedDomainService == null) {
            throw new BadRequestException("Service to update Type with id " + id);
        }


        return updatedDomainService;
    }

}
