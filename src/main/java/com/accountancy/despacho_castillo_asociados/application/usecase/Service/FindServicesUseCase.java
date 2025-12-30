package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindServicesUseCase {

    private final ServiceRepository serviceRepository;

    public FindServicesUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public PageResult<DomainService> execute(String name, int page, int size) {

        if (name != null && !name.isEmpty()) {

            PageResult<DomainService> servicesByName = serviceRepository.findByContainsNameLetterUseCase(name, page, size);

            if (servicesByName.content().isEmpty()) {
                throw new EmptyListException("No services found with name containing: " + name);
            }

            return servicesByName;
        }

        PageResult<DomainService> domainServices = serviceRepository.findAll(page, size);

        if (domainServices.content().isEmpty()) {
            throw new EmptyListException("No services found");
        }

        return domainServices;
    }

}
