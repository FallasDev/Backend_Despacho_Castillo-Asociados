package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.CustomFieldWithOrder;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class UpdateServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository serviceCustomFieldsRepository;
    private final CustomFieldRepository customFieldRepository;
    private final ServiceRepository serviceRepository;
    private final Messages messages;

    public UpdateServiceCustomFieldsUseCase(ServiceCustomFieldsRepository serviceCustomFieldsRepository, CustomFieldRepository customFieldRepository, ServiceRepository serviceRepository, Messages messages) {
        this.serviceCustomFieldsRepository = serviceCustomFieldsRepository;
        this.customFieldRepository = customFieldRepository;
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

    @Transactional
    public ServiceCustomField execute(ServiceCustomFieldRequest request, int id) {

        if (request == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.cannot_be_null"));
        }

        System.out.println("Updating ServiceCustomField with ID: " + id);

        List<CustomField> customFields = customFieldRepository.findAllByIds(request.getCustomFieldIds().stream().map(CustomFieldWithOrder::getCustomFieldId).toList());


        DomainService service = serviceRepository.findById(request.getServiceId()).orElseThrow(
                () -> new BadRequestException(
                        messages.get("servicecustomfield.exception.create.service.not_found", new Object[]{request.getServiceId()})));


        System.out.println("Found Service: " + service);
        System.out.println("Found CustomFields: " + customFields);

        ServiceCustomField updated = serviceCustomFieldsRepository.update(
                request,
                service,
                customFields,
                id
        );

        System.out.println("Updated ServiceCustomField: " + updated);

        if (updated == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.update.failed"));
        }

        return updated;

    }

}
