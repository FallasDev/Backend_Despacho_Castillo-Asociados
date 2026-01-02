package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;



import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;
    private final ServiceRepository serviceRepository;
    private final CustomFieldRepository customFieldRepository;

    public CreateServiceCustomFieldsUseCase(ServiceCustomFieldsRepository repository,
                                            ServiceRepository serviceRepository,
                                            CustomFieldRepository customFieldRepository) {
        this.repository = repository;
        this.serviceRepository = serviceRepository;
        this.customFieldRepository = customFieldRepository;
    }

    public ServiceCustomField execute(ServiceCustomFieldRequest request) {

        if (request == null) {
            throw new BadRequestException("Service Custom Field cannot be null");
        }


        Optional<ServiceCustomField> existingRelation = repository.findByServiceIdAndCustomFieldId(request.getServiceId(),
                request.getCustomFieldId());

        Optional<DomainService> service = serviceRepository.findById(request.getServiceId());
        Optional<CustomField> customField = customFieldRepository.findById(request.getCustomFieldId());

        if (existingRelation.isPresent() && existingRelation.get().isActive()) {
            throw new BadRequestException("Service Custom Field relation already exists for " +
                    "service ID " + request.getServiceId() + " and custom field ID " + request.getCustomFieldId());
        }

        if (service.isEmpty()) {
            throw new BadRequestException("Service with ID " + request.getServiceId() + " does not exist");
        }

        if (customField.isEmpty()) {
            throw new BadRequestException("Custom Field with ID " + request.getCustomFieldId() + " does not exist");
        }

        if (!service.get().isActive()) {
            throw new BadRequestException("Service with ID " + request.getServiceId() + " is not active");
        }

        if (!customField.get().isActive()) {
            throw new BadRequestException("Custom Field with ID " + request.getCustomFieldId() + " is not active");
        }

        Optional<ServiceCustomField> inactive = repository.findByServiceIdAndCustomFieldIdAndIsInactive(request.getServiceId(), request.getCustomFieldId());

        if (inactive.isPresent()) {
            ServiceCustomField reactivatedServiceCustomField = inactive.get();
            reactivatedServiceCustomField.setActive(true);
            repository.activate(reactivatedServiceCustomField.getId());
            return reactivatedServiceCustomField;
        }

        return repository.create(request);

    }

}
