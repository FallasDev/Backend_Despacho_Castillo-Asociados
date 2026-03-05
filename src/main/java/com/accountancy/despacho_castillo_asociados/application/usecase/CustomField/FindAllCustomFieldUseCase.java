package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class FindAllCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final Messages messages;

    public FindAllCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        this.messages = messages;
        this.customFieldRepository = customFieldRepository;
    }

    public PageResult<CustomField> execute(int page, int size) {
        PageResult<CustomField> customFields = customFieldRepository.findAll(page, size);

        if (customFields.content().isEmpty()) {
            throw new EmptyListException(messages.get("customfield.exception.fetch.all.none"));
        }

        return customFields;
    }


}
