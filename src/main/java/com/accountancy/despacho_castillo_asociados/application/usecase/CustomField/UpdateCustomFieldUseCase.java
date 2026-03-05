package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final TypeRepository typeRepository;
    private final Messages messages;

    public UpdateCustomFieldUseCase(CustomFieldRepository customFieldRepository,
                                    TypeRepository typeRepository,
                                    Messages messages) {
        this.messages = messages;
        this.customFieldRepository = customFieldRepository;
        this.typeRepository = typeRepository;
    }

    public CustomField execute(CustomFieldRequest customFieldRequest, int id) {

        if (customFieldRequest.getName() == null || customFieldRequest.getName().isEmpty()) {
            throw new BadRequestException(messages.get("customfield.exception.update.cannot_be_null"));
        }

        Type type = typeRepository.findById(customFieldRequest.getTypeId()).orElse(null);

        if (type == null) {
            throw new BadRequestException(messages.get("customfield.exception.update.type.invalid", new Object[]{id}));
        }

        Optional<CustomField> existingCustomField = customFieldRepository.findById(id);

        if (existingCustomField.isEmpty()) {
            throw new BadRequestException(messages.get("customfield.exception.update.notfound", new Object[]{id}));
        }

        if (!existingCustomField.get().isActive()) {
            throw new BadRequestException(messages.get("customfield.exception.update.is_not_active", new Object[]{id}));
        }

        CustomField customField = customFieldRepository.update(customFieldRequest, id, type);

        if (customField == null) {
            throw new BadRequestException(messages.get("customfield.exception.update.failed"));
        }

        return customField;

    }


}
