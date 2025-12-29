package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class FindAllCustomFieldUseCase {

    private CustomFieldRepository customFieldRepository;

    public FindAllCustomFieldUseCase(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    public List<CustomField> execute() {
        List<CustomField> customFields = customFieldRepository.findAll();

        if (customFields.isEmpty()) {
            throw new EntityNotFoundException("No custom fields found");
        }

        return customFields;
    }


}
