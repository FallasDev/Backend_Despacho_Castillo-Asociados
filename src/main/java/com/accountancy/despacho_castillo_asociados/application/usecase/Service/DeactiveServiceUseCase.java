package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;

public class DeactiveServiceUseCase {

    private final ServiceRepository serviceRepository;

    public DeactiveServiceUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void execute(int id) {
        boolean result = serviceRepository.deactivate(id);

        if (!result) {
            throw new RuntimeException("Failed to deactivate service with id " + id);
        }
    }

}
