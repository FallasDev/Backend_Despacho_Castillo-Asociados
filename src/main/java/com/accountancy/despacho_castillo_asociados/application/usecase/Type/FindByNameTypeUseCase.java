package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import jakarta.persistence.EntityNotFoundException;


import java.util.Optional;

public class FindByNameTypeUseCase {

    private final TypeRepository typeRepository;

    public FindByNameTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(String name) {

        Optional<Type> type = typeRepository.findByName(name);

        if (type.isEmpty() || !type.get().isActive()) {
            throw new EntityNotFoundException("Type with name " + name + " not found");
        }

        // Return the Type if found, otherwise return null
        return type.get();

    }

}
