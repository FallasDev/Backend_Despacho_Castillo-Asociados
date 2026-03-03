package com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields.ServiceCustomFieldsRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveServiceCustomFieldsUseCase {

    private final ServiceCustomFieldsRepository repository;
    private final Messages messages;

    public DeactiveServiceCustomFieldsUseCase(ServiceCustomFieldsRepository repository, Messages messages) {
        this.repository = repository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = repository.deactivate(id);

        if (!result) {
            throw new BadRequestException(messages.get("servicecustomfield.exception.deactive", new Object[]{id}));
        }

    }

}
