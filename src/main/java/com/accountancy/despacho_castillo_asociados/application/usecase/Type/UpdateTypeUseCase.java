package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

public class UpdateTypeUseCase {

    private final TypeRepository typeRepository;

    public UpdateTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(Type type, int id) {
        Type updatedType = typeRepository.update(type, id);

        if (updatedType == null) {
            throw new EntityNotFoundException("Failed to update Type with id " + id);
        }

        return updatedType;
    }

}
