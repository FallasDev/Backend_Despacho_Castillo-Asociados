package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateServiceUseCase {

    private final ServiceRepository serviceRepository;

    public CreateServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public DomainService execute(ServiceRequest service) {

        if (service == null) {
            throw new BadRequestException("Service cannot be null");
        }

        if (service.getName() == null || service.getName().isEmpty()) {
            throw new BadRequestException("Service name cannot be null or empty");
        }

        boolean existingService = serviceRepository.existsByNameAndIsActive(service.getName());

        if (existingService) {
            throw new BadRequestException("Service with name " + service.getName() + " already exists");
        }

        Optional<DomainService> inactiveService = serviceRepository.findByNameAndIsInactive(service.getName());

        if (inactiveService.isPresent()) {
            DomainService reactivatedDomainService = inactiveService.get();
            reactivatedDomainService.setActive(true);
            serviceRepository.activate(reactivatedDomainService.getId());
            return reactivatedDomainService;
        }

        return serviceRepository.create(service);

    }

}
