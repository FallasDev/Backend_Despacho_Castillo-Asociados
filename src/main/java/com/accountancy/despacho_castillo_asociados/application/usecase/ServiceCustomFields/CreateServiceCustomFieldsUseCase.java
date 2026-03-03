package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;



import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;
    private final ServiceRepository serviceRepository;
    private final CustomFieldRepository customFieldRepository;
    private final Messages messages;

    public CreateServiceCustomFieldsUseCase(ServiceCustomFieldsRepository repository,
                                            ServiceRepository serviceRepository,
                                            CustomFieldRepository customFieldRepository,
                                            Messages messages) {
        this.messages = messages;
        this.repository = repository;
        this.serviceRepository = serviceRepository;
        this.customFieldRepository = customFieldRepository;
    }

    public ServiceCustomField execute(ServiceCustomFieldRequest request) {

        if (request == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.cannot_be_null"));
        }


        Optional<ServiceCustomField> existingRelation = repository.findByServiceIdAndCustomFieldId(request.getServiceId(),
                request.getCustomFieldId());

        Optional<DomainService> service = serviceRepository.findById(request.getServiceId());
        Optional<CustomField> customField = customFieldRepository.findById(request.getCustomFieldId());

        if (existingRelation.isPresent() && existingRelation.get().isActive()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.name.relation.already.exists"));
        }

        if (service.isEmpty()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.service.not_found",
                    new Object[]{request.getServiceId()}));
        }

        if (customField.isEmpty()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.customfield.not_found",
                    new Object[]{request.getCustomFieldId()}));
        }

        if (!service.get().isActive()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.service.not_found",
                    new Object[]{request.getServiceId()}));
        }

        if (!customField.get().isActive()) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.customfield.not_found",
                    new Object[]{request.getCustomFieldId()}));
        }

        Optional<ServiceCustomField> inactive = repository.findByServiceIdAndCustomFieldIdAndIsInactive(request.getServiceId(), request.getCustomFieldId());

        if (inactive.isPresent()) {
            ServiceCustomField reactivatedServiceCustomField = inactive.get();
            reactivatedServiceCustomField.setActive(true);
            repository.activate(reactivatedServiceCustomField.getId());
            return reactivatedServiceCustomField;
        }

        ServiceCustomField created = repository.create(request);

        if (created == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.failed"));
        }

        return created;

    }

}
