package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdTypeUseCase {

    private final TypeRepository typeRepository;

    private final Messages messages;

    public FindByIdTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.typeRepository = typeRepository;
        this.messages = messages;
    }

    public Type execute(int id) {
        Optional<Type> type = typeRepository.findById(id);

        if (type.isEmpty() || !type.get().isActive()) {
            throw new EntityNotFoundException(messages.get("type.exception.fetch.by_id.notfound", new Object[]{id}));
        }

        return type.get();
    }

}
