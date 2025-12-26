package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class CreateTypeUseCase {


    private final TypeRepository typeRepository;

    public CreateTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(Type type) {
        Type createdType = typeRepository.create(type);

        if (createdType == null) {
            throw new EntityNotFoundException("Failed to create Type");
        }

        return createdType;
    }

}
