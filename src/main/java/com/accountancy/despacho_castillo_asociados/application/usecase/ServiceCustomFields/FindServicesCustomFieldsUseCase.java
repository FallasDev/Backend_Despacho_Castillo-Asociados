package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.transaction.Transactional;

public class FindServicesCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;

    private final Messages messages;

    public FindServicesCustomFieldsUseCase(ServiceCustomFieldsRepository repository, Messages messages) {
        this.repository = repository;
        this.messages = messages;
    }

    @Transactional
    public PageResult<ServiceCustomField> execute(int serviceId, int page, int size) {

            if (serviceId > 0) {

                PageResult<ServiceCustomField> servicesCustomFieldsByServiceId = repository.findByServiceId(serviceId, page, size);

                if (servicesCustomFieldsByServiceId.content().isEmpty()) {
                    throw new EmptyListException(messages.get("servicecustomfield.exception.fetch.by_service_id.notfound", new Object[]{serviceId}));
                }

                return servicesCustomFieldsByServiceId;

            }

            PageResult<ServiceCustomField> serviceCustomFields = repository.findAll(page, size);
            if (serviceCustomFields.content().isEmpty()) {
                throw new EmptyListException(messages.get("servicecustomfield.exception.fetch.all.none"));
            }
            return serviceCustomFields;


        
    }



}
