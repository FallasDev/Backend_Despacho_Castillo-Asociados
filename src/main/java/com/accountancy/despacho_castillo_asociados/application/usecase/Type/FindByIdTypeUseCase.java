package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdTypeUseCase {

    private final TypeRepository typeRepository;

    public FindByIdTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(int id) {
        Optional<Type> type = typeRepository.findById(id);

        if (type.isEmpty() || !type.get().isActive()) {
            throw new EntityNotFoundException("Type with id " + id + " not found");
        }

        return type.get();
    }

}
