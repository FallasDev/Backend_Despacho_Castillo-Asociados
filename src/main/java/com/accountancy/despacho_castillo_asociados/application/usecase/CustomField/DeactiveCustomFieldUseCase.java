package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactiveCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;
    private final Messages messages;

    public DeactiveCustomFieldUseCase(CustomFieldRepository customFieldRepository, Messages messages) {
        this.customFieldRepository = customFieldRepository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = customFieldRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException(messages.get("customfield.exception.deactive", new Object[]{id} ) );
        }
    }

}
