package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindServicesCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;

    public FindServicesCustomFieldsUseCase(ServiceCustomFieldsRepository repository) {
        this.repository = repository;
    }

    public PageResult<ServiceCustomField> execute(int serviceId, int page, int size) {

            if (serviceId > 0) {

                PageResult<ServiceCustomField> servicesCustomFieldsByServiceId = repository.findByServiceId(serviceId, page, size);

                if (servicesCustomFieldsByServiceId.content().isEmpty()) {
                    throw new EmptyListException("No custom fields found for service ID: " + serviceId);
                }

                return servicesCustomFieldsByServiceId;

            }

            PageResult<ServiceCustomField> serviceCustomFields = repository.findAll(page, size);
            if (serviceCustomFields.content().isEmpty()) {
                throw new EmptyListException("No custom fields found");
            }
            return serviceCustomFields;


        
    }



}
