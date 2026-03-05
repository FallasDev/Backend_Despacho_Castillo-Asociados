package com.accountancy.despacho_castillo_asociados.application.usecase.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields.FormalitieCustomFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveFormalitieCustomFieldsUseCase {

    private final FormalitieCustomFieldRepository repository;

    private final Messages messages;

    public DeactiveFormalitieCustomFieldsUseCase(FormalitieCustomFieldRepository repository, Messages messages) {
        this.messages = messages;
        this.repository = repository;
    }

    public void execute(int id) {
        boolean result = repository.deactivate(id);

        if (!result) {
            throw new BadRequestException(messages.get("formalitycustomfield.exception.deactive", new Object[]{id}));
        }

    }

}
