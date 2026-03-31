package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindAllServicesUseCase {

    private final ServiceRepository serviceRepository;
    private final  Messages messages;

    public FindAllServicesUseCase(ServiceRepository serviceRepository, Messages messages) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

     public List<DomainService> execute() {

        List<DomainService> services = serviceRepository.findAllWithoutPagination();

        if (services.isEmpty()) {
            throw new EmptyListException("service.exception.fetch.all.none");
        }

        return services;

     }

}
