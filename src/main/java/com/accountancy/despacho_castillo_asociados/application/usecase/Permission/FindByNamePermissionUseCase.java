package com.accountancy.despacho_castillo_asociados.application.usecase.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByNamePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public FindByNamePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Permission name cannot be null or empty");
        }

        Optional<Permission> permission = permissionRepository.fintByName(name);

        if (permission.isEmpty()) {
            throw new EntityNotFoundException("Permission with name " + name + " not found");
        }

        return permission.get();
    }

}

