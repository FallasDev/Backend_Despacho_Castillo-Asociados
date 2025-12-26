package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;


public class FindAllTypeUseCase {

    private final TypeRepository typeRepository;

    public FindAllTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> execute() {

        List<Type> types = typeRepository.findAll();

        if (types.isEmpty()) {
            throw new EntityNotFoundException("No types found");
        }

        return types;
    }

}
