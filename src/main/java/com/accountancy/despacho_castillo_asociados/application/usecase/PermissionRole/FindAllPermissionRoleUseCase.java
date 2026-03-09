package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindAllPermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public FindAllPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    public PageResult<PermissionRole> execute(int page, int size) {

        PageResult<PermissionRole> permissionRoles = permissionRoleRepository.findAll(page, size);

        if (permissionRoles.content().isEmpty()) {
            throw new EmptyListException("No permission roles found");
        }

        return permissionRoles;
    }

}

