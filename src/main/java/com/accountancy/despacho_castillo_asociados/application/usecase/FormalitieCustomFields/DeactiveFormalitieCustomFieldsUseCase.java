package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository repository;

    public DeactiveFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        boolean result = repository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate FormalitieCustomField with id: " + id);
        }

    }

}
