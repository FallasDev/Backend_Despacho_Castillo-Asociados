package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;

    public DeactiveServiceCustomFieldsUseCase(ServiceCustomFieldsRepository repository) {
        this.repository = repository;
    }

    public void execute(int id) {
        boolean result = repository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate ServiceCustomField with id: " + id);
        }

    }

}
