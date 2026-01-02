package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository serviceCustomFieldsRepository;

    public UpdateServiceCustomFieldsUseCase(ServiceCustomFieldsRepository serviceCustomFieldsRepository) {
        this.serviceCustomFieldsRepository = serviceCustomFieldsRepository;
    }

    public ServiceCustomField execute(ServiceCustomFieldRequest request, int id) {

        if (request == null) {
            throw new BadRequestException("Service cannot be null");
        }


        Optional<ServiceCustomField> existingService = serviceCustomFieldsRepository.findByServiceIdAndCustomFieldId(
                request.getServiceId(),
                request.getCustomFieldId());


        if (existingService.isEmpty()) {
            throw new BadRequestException("Service Custom Field relation does not exist for service ID " + request.getServiceId() + " and custom field ID " + request.getCustomFieldId());
        }

        if (!existingService.get().isActive()) {
            throw new BadRequestException("Service Custom Field relation for service ID " + request.getServiceId() + " and custom field ID " + request.getCustomFieldId() + " is not active");
        }

        ServiceCustomField updated = serviceCustomFieldsRepository.update(request, id);

        if (updated == null) {
            throw new BadRequestException("Service Custom Field relation to update with id " + id);
        }

        return updated;

    }

}
