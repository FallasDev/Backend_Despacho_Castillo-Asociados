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

    @Transactional
    public ServiceCustomField execute(ServiceCustomFieldRequest request) {

        if (request == null) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.create.cannot_be_null"));
        }

        List<CustomField> customFields = customFieldRepository.findAllByIds(request.getCustomFieldIds().stream().map(CustomFieldWithOrder::getCustomFieldId).toList());

        Optional<ServiceCustomField> inactive = repository.findByNameAndIsInactive(request.getName());

        if (inactive.isPresent()) {
            ServiceCustomField reactivatedServiceCustomField = inactive.get();
            reactivatedServiceCustomField.setActive(true);
            repository.activate(reactivatedServiceCustomField.getId());
            return  reactivatedServiceCustomField;
        }

        DomainService service = serviceRepository.findById(request.getServiceId()).orElseThrow(
                () -> new BadRequestException(
                        messages.get("servicecustomfield.exception.create.service.not_found", new Object[]{request.getServiceId()})));


        return repository.create(request, service, customFields);

    }

}
