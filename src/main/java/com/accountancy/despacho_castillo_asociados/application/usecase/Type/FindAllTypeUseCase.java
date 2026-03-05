package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


public class FindAllTypeUseCase {

    private final TypeRepository typeRepository;

    private final Messages messages;

    public FindAllTypeUseCase(TypeRepository typeRepository, Messages messages) {
        this.messages = messages;
        this.typeRepository = typeRepository;
    }

    public PageResult<Type> execute(int page, int size) {

        PageResult<Type> types = typeRepository.findAll(page, size);

        if (types.content().isEmpty()) {
            throw new EmptyListException(messages.get("type.exception.fetch.all.none"));
        }

        return types;
    }

}
