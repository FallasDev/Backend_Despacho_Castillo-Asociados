package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class UpdateTypeUseCase {

    private final TypeRepository typeRepository;

    public UpdateTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(TypeRequest type, int id) {


        if (type == null) {
            throw new BadRequestException("Type cannot be null");
        }

        if (type.getName() == null || type.getName().isEmpty()) {
            throw new BadRequestException("Type name cannot be null or empty");
        }

        Optional<Type> existingType = typeRepository.findById(id);


        if (existingType.isEmpty()) {
            throw new BadRequestException("Type with id " + id + " does not exist");
        }

        if (!existingType.get().isActive()) {
            throw new BadRequestException("Type with id " + id + " is not active");
        }

        Type updatedType = typeRepository.update(type, id);

        if (updatedType == null) {
            throw new BadRequestException("Failed to update Type with id " + id);
        }

        return updatedType;
    }

}
