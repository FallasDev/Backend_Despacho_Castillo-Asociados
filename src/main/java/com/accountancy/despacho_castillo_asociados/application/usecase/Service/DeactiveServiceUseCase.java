package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveServiceUseCase {

    private final ServiceRepository serviceRepository;
    private final ServiceCustomFieldsRepository serviceCustomFieldsRepository;

    private final Messages messages;

    public DeactiveServiceUseCase(ServiceRepository serviceRepository, Messages messages, ServiceCustomFieldsRepository serviceCustomFieldsRepository) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
        this.serviceCustomFieldsRepository = serviceCustomFieldsRepository;
    }

    public void execute(int id) {
        boolean result = serviceRepository.deactivate(id);

        System.out.println("Deactivating service with ID: " + id + ", result: " + result);

        if (!result) {
            throw new BadRequestException(messages.get("service.exception.deactive", new Object[]{id}));
        }

        serviceCustomFieldsRepository.deactivateByServiceId(id);
    }

}
