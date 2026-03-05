package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class UpdateTypeUseCase {

    private final TypeRepository typeRepository;

    private final Messages messages;

    public UpdateTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public Type execute(TypeRequest type, int id) {

        if (type == null) {
            throw new BadRequestException(messages.get("type.exception.update.cannot_be_null"));
        }

        if (type.getName() == null || type.getName().isEmpty()) {
            throw new BadRequestException(messages.get("type.exception.update.name.cannot_be_null"));
        }

        Optional<Type> existingType = typeRepository.findById(id);


        if (existingType.isEmpty()) {
            throw new BadRequestException(messages.get("type.exception.update.notfound", new Object[]{id}));
        }

        if (!existingType.get().isActive()) {
            throw new BadRequestException(messages.get("type.exception.update.is_not_active", new Object[]{id}));
        }

        if (existingType.get().getName().equals(type.getName())) {
            throw new BadRequestException(messages.get("type.exception.update.name.already.exists"));
        }

        Type updatedType = typeRepository.update(type, id);

        if (updatedType == null) {
            throw new BadRequestException(messages.get("type.exception.update.failed"));
        }

        return updatedType;
    }

}
