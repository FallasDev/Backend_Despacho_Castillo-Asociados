package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class FindByFormalitieIdUseCase {

    private ServiceCustomFieldsRepository serviceCustomFieldRepository;

    public FindByFormalitieIdUseCase(ServiceCustomFieldsRepository serviceCustomFieldRepository) {
        this.serviceCustomFieldRepository = serviceCustomFieldRepository;
    }

    @Transactional
    public ServiceCustomField execute(int formalitieId) {

        Optional<ServiceCustomField> serviceCustomField = serviceCustomFieldRepository.findByFormalitieId(formalitieId);

        if (serviceCustomField.isEmpty()) {
            throw new BadRequestException("No se encontró el campo personalizado para la formalitie con ID: " + formalitieId);
        }

        return serviceCustomField.get();

    }

}
