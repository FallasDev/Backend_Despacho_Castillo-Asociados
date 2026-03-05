package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class CreatePermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public CreatePermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    public PermissionRole execute(PermissionRoleRequest permissionRoleRequest) {

        if (permissionRoleRequest == null) {
            throw new BadRequestException("PermissionRole cannot be null");
        }

        if (permissionRoleRequest.getIdRole() <= 0) {
            throw new BadRequestException("PermissionRole idRole cannot be null or less than 1");
        }

        if (permissionRoleRequest.getPermission() == null || permissionRoleRequest.getPermission().getId() <= 0) {
            throw new BadRequestException("PermissionRole permission cannot be null or invalid");
        }

        return permissionRoleRepository.create(permissionRoleRequest);
    }

}

