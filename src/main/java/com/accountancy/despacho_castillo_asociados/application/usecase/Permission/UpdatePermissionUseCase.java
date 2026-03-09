package com.accountancy.despacho_castillo_asociados.application.usecase.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.Permission.PermissionRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class UpdatePermissionUseCase {

    private final PermissionRepository permissionRepository;

    public UpdatePermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission execute(PermissionRequest permissionRequest, int id) {

        if (permissionRequest == null) {
            throw new BadRequestException("Permission cannot be null");
        }

        if (permissionRequest.getName() == null || permissionRequest.getName().isEmpty()) {
            throw new BadRequestException("Permission name cannot be null or empty");
        }

        Optional<Permission> existingPermission = permissionRepository.findById(id);

        if (existingPermission.isEmpty()) {
            throw new EntityNotFoundException("Permission with id " + id + " does not exist");
        }

        Permission updatedPermission = permissionRepository.update(new Permission(id, permissionRequest.getName(), permissionRequest.getDescription()), id);

        if (updatedPermission == null) {
            throw new BadRequestException("Failed to update Permission with id " + id);
        }

        return updatedPermission;
    }

}

