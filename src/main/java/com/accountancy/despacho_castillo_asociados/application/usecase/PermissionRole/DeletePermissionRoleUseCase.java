package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeletePermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public DeletePermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    public void execute(int id) {
        boolean result = permissionRoleRepository.delete(id);

        if (!result) {
            throw new BadRequestException("Failed to delete PermissionRole with id " + id);
        }
    }

}

