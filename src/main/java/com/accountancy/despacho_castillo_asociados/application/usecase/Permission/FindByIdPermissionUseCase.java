package com.accountancy.despacho_castillo_asociados.application.usecase.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdPermissionUseCase {

    private final PermissionRepository permissionRepository;

    public FindByIdPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(int id) {
        Optional<Permission> permission = permissionRepository.findById(id);

        if (permission.isEmpty()) {
            throw new EntityNotFoundException("Permission with id " + id + " not found");
        }

        return permission.get();
    }

}

