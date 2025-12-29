package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactiveTypeUseCase {

    private final TypeRepository typeRepository;

    public DeactiveTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public void execute(int id) {
        boolean result = typeRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate Type with id " + id);
        }
    }

}
