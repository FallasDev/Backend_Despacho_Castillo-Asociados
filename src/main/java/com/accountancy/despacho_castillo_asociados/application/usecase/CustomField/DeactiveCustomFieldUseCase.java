package com.accountancy.despacho_castillo_asociados.application.usecase.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.CustomField.CustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactiveCustomFieldUseCase {

    private final CustomFieldRepository customFieldRepository;

    public DeactiveCustomFieldUseCase(CustomFieldRepository customFieldRepository) {
        this.customFieldRepository = customFieldRepository;
    }

    public void execute(int id) {
        boolean result = customFieldRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate CustomField with id " + id);
        }
    }

}
