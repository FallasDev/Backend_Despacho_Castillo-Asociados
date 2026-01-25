package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindServicesUseCase {

    private final ServiceRepository serviceRepository;
    private final Messages messages;

    public FindServicesUseCase(ServiceRepository serviceRepository, Messages messages) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

    public PageResult<DomainService> execute(String name, int page, int size) {

        if (name != null && !name.isEmpty()) {

            PageResult<DomainService> servicesByName = serviceRepository.findByContainsNameLetterUseCase(name, page, size);

            if (servicesByName.content().isEmpty()) {
                throw new EmptyListException(messages.get("service.exception.fetch.by_name_like.none"));
            }

            return servicesByName;
        }

        PageResult<DomainService> domainServices = serviceRepository.findAll(page, size);

        if (domainServices.content().isEmpty()) {
            throw new EmptyListException(messages.get("service.exception.fetch.all.none"));
        }

        return domainServices;
    }

}
