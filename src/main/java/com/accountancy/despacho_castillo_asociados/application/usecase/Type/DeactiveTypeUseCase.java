package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactiveTypeUseCase {

    private final TypeRepository typeRepository;


    private final Messages messages;

    public DeactiveTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public void execute(int id) {

        boolean exists = typeRepository.hasActiveAssociations(id);

        if (exists) {
            throw new BadRequestException(messages.get("type.exception.deactive.has_associations", new Object[]{id}));
        }

        boolean result = typeRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException(messages.get("type.exception.deactive", new Object[]{id}));
        }
    }

}
