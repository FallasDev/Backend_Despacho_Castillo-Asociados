package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;


import java.util.Optional;

public class FindByNameTypeUseCase {

    private final TypeRepository typeRepository;

    private final Messages messages;

    public FindByNameTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public Type execute(String name) {

        Optional<Type> type = typeRepository.findByName(name);

        if (type.isEmpty() || !type.get().isActive()) {
            throw new EntityNotFoundException(messages.get("type.exception.fetch.by_name_like.none"));
        }

        // Return the Type if found, otherwise return null
        return type.get();

    }

}
