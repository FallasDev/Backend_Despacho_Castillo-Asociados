package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class CreateCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final TypeRepository typeRepository;

    public CreateCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository) {
        this.customFieldRepository = customFieldRepository;
        this.typeRepository = typeRepository;
    }

    public CustomField execute(CustomFieldRequest customField) {

        if (customField == null) {
            throw new BadRequestException("CustomField cannot be null");
        }

        if (customField.getName() == null || customField.getName().isEmpty()) {
            throw new BadRequestException("CustomField name cannot be null or empty");
        }

        Type type = typeRepository.findById(customField.getTypeId()).orElse(null);

        if (type == null || !type.isActive()) {
            throw new BadRequestException("CustomField type cannot be null or inactive");
        }

        boolean existingCustomField = customFieldRepository.existsByNameAndIsActive(customField.getName());

        if (existingCustomField) {
            throw new BadRequestException("CustomField with name " + customField.getName() + " already exists");
        }

        Optional<CustomField> inactiveCustomField = customFieldRepository.findByNameAndIsInactive(customField.getName());

        if (inactiveCustomField.isPresent()) {
            CustomField reactivatedCustomField = inactiveCustomField.get();
            reactivatedCustomField.setActive(true);
            customFieldRepository.activate(reactivatedCustomField.getId());
            return reactivatedCustomField;
        }

        return customFieldRepository.create(customField,type);
    }

}
