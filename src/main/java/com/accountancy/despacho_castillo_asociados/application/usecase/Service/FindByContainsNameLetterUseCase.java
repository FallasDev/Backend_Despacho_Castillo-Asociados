package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindByContainsNameLetterUseCase {

    private final ServiceRepository serviceRepository;

    public FindByContainsNameLetterUseCase(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public PageResult<DomainService> execute(String rgxName, int page, int size) {
        PageResult<DomainService> domainServices = serviceRepository.findByContainsNameLetterUseCase(rgxName,page,size);

        if (domainServices.content().isEmpty()) {
            throw new EmptyListException("No services found");
        }

        return domainServices;

    }

}
