package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindByContainsNameLetterUseCase {

    private final ServiceRepository serviceRepository;
    private final Messages messages;

    public FindByContainsNameLetterUseCase(ServiceRepository serviceRepository, Messages messages) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

    public PageResult<DomainService> execute(String rgxName, int page, int size) {
        PageResult<DomainService> domainServices = serviceRepository.findByContainsNameLetterUseCase(rgxName,page,size);

        if (domainServices.content().isEmpty()) {
            throw new EmptyListException(messages.get("service.exception.fetch.all.none"));
        }

        return domainServices;

    }

}
