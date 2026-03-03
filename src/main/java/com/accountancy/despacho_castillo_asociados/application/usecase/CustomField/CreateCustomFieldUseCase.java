package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class CreateCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final TypeRepository typeRepository;
    private Messages messages;

    public CreateCustomFieldUseCase(CustomFieldRepository customFieldRepository,
                                    TypeRepository typeRepository,
                                    Messages messages) {
        this.customFieldRepository = customFieldRepository;
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public CustomField execute(CustomFieldRequest customField) {

        if (customField == null) {
            throw new BadRequestException(messages.get("customfield.exception.create.cannot_be_null"));
        }

        if (customField.getName() == null || customField.getName().isEmpty()) {
            throw new BadRequestException(messages.get("customfield.exception.create.name.cannot_be_null"));
        }

        Type type = typeRepository.findById(customField.getTypeId()).orElse(null);

        if (type == null || !type.isActive()) {
            throw new BadRequestException(messages.get("customfield.exception.create.type.invalid", new Object[]{customField.getTypeId()}));
        }

        boolean existingCustomField = customFieldRepository.existsByNameAndIsActive(customField.getName());

        if (existingCustomField) {
            throw new BadRequestException(messages.get("customfield.exception.create.already.exists"));
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
