package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

import java.util.Optional;


public class FindByIdServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;


    public FindByIdServiceCustomFieldsUseCase(ServiceCustomFieldsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ServiceCustomField execute(int id) {
        Optional<ServiceCustomField> serviceCustomFieldOptional = repository.findById(id);

        if (serviceCustomFieldOptional.isEmpty()) {
            throw new BadRequestException("ServiceCustomField with id " + id + " not found");
        }

        return serviceCustomFieldOptional.get();
    }

}
