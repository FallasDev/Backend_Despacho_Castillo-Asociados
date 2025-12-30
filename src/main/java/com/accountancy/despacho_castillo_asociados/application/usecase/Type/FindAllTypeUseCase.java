package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


public class FindAllTypeUseCase {

    private final TypeRepository typeRepository;

    public FindAllTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public PageResult<Type> execute(int page, int size) {

        PageResult<Type> types = typeRepository.findAll(page, size);

        if (types.content().isEmpty()) {
            throw new EmptyListException("No types found");
        }

        return types;
    }

}
