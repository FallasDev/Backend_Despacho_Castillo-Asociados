package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository serviceCustomFieldsRepository;
    private final Messages messages;

    public UpdateServiceCustomFieldsUseCase(ServiceCustomFieldsRepository serviceCustomFieldsRepository, Messages messages) {
        this.serviceCustomFieldsRepository = serviceCustomFieldsRepository;
        this.messages = messages;
    }

    public ServiceCustomField execute(ServiceCustomFieldRequest request, int id) {

        if (request == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.cannot_be_null"));
        }


        Optional<ServiceCustomField> existingService = serviceCustomFieldsRepository.findByServiceIdAndCustomFieldId(
                request.getServiceId(),
                request.getCustomFieldId());


        if (existingService.isEmpty()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.notfound", new Object[]{request.getServiceId(), request.getCustomFieldId()}));
        }

        if (!existingService.get().isActive()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.notfound", new Object[]{request.getServiceId(), request.getCustomFieldId()}));
        }

        ServiceCustomField updated = serviceCustomFieldsRepository.update(request, id);

        if (updated == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.failed"));
        }

        return updated;

    }

}
