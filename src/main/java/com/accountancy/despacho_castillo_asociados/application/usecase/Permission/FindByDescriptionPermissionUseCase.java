package com.accountancy.despacho_castillo_asociados.application.usecase.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByDescriptionPermissionUseCase {

    private final PermissionRepository permissionRepository;

    public FindByDescriptionPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(String description) {

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Permission description cannot be null or empty");
        }

        Optional<Permission> permission = permissionRepository.fintByDescription(description);

        if (permission.isEmpty()) {
            throw new EntityNotFoundException("Permission with description " + description + " not found");
        }

        return permission.get();
    }

}

