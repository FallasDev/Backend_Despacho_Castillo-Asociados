package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final Messages messages;

    public FindByIdCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        this.messages = messages;
        this.customFieldRepository = customFieldRepository;
    }

    public CustomField execute(int id) {

        Optional<CustomField> customField = customFieldRepository.findById(id);

        if (customField.isEmpty() || !customField.get().isActive()) {
            throw new EntityNotFoundException(messages.get("customfield.exception.fetch.by_id.notfound", new Object[]{id}));
        }



        return customField.get();

    }

}
