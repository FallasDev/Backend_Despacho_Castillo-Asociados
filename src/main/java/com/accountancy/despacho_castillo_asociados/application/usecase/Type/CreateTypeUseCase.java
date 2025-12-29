package com.accountancy.despacho_castillo_asociados.application.usecase.Type;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;


public class CreateTypeUseCase {


    private final TypeRepository typeRepository;

    public CreateTypeUseCase(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public Type execute(TypeRequest type) {

        if (type == null) {
            throw new BadRequestException("Type cannot be null");
        }

        if (type.getName() == null || type.getName().isEmpty()) {
            throw new BadRequestException("Type name cannot be null or empty");
        }

        boolean existingType = typeRepository.existsByNameAndIsActive(type.getName());

        if (existingType) {
            throw new BadRequestException("Type with name " + type.getName() + " already exists");
        }

        Optional<Type> inactiveType = typeRepository.findByNameAndIsInactive(type.getName());

        if (inactiveType.isPresent()) {
            Type reactivatedType = inactiveType.get();
            reactivatedType.setActive(true);
            typeRepository.activate(reactivatedType.getId());
            return reactivatedType;
        }

        return typeRepository.create(type);
    }

}
