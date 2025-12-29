package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdCustomFieldUseCase {

    private CustomFieldRepository customFieldRepository;

    public FindByIdCustomFieldUseCase(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    public CustomField execute(int id) {

        Optional<CustomField> customField = customFieldRepository.findById(id);

        if (customField.isEmpty() || !customField.get().isActive()) {
            throw new EntityNotFoundException("Custom field with id " + id + " not found");
        }



        return customField.get();

    }

}
