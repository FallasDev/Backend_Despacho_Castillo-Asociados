package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final TypeRepository typeRepository;

    public UpdateCustomFieldUseCase(CustomFieldRepository customFieldRepository, TypeRepository typeRepository) {
        this.customFieldRepository = customFieldRepository;
        this.typeRepository = typeRepository;
    }

    public CustomField execute(CustomFieldRequest customFieldRequest, int id) {

        if (customFieldRequest.getName() == null || customFieldRequest.getName().isEmpty()) {
            throw new BadRequestException("CustomField name cannot be null or empty");
        }

        Type type = typeRepository.findById(customFieldRequest.getTypeId()).orElse(null);

        if (type == null) {
            throw new BadRequestException("CustomField type cannot be null");
        }

        Optional<CustomField> existingCustomField = customFieldRepository.findById(id);

        if (existingCustomField.isEmpty()) {
            throw new BadRequestException("CustomField with id " + id + " does not exist");
        }

        if (!existingCustomField.get().isActive()) {
            throw new BadRequestException("CustomField with id " + id + " is not active");
        }

        CustomField customField = customFieldRepository.update(customFieldRequest, id, type);

        if (customField == null) {
            throw new BadRequestException("Failed to update CustomField with id " + id);
        }

        return customField;

    }


}
