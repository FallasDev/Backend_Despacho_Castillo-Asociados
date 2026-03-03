package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;


public class CreateTypeUseCase {


    private final TypeRepository typeRepository;

    private final Messages messages;

    public CreateTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public Type execute(TypeRequest type) {

        if (type == null) {
            throw new BadRequestException(messages.get("type.exception.create.cannot_be_null"));
        }

        if (type.getName() == null || type.getName().isEmpty()) {
            throw new BadRequestException(messages.get("type.exception.create.name.cannot_be_null"));
        }

        boolean existingType = typeRepository.existsByNameAndIsActive(type.getName());

        if (existingType) {
            throw new BadRequestException(messages.get("type.exception.create.already.exists"));
        }



        Optional<Type> inactiveType = typeRepository.findByNameAndIsInactive(type.getName());

        if (inactiveType.isPresent()) {
            Type reactivatedType = inactiveType.get();
            reactivatedType.setActive(true);
            typeRepository.activate(reactivatedType.getId());
            return reactivatedType;
        }

        Type createdType = typeRepository.create(type);

        if (createdType == null) {
            throw new BadRequestException(messages.get("type.exception.create.failed"));
        }

        return createdType;
    }

}
