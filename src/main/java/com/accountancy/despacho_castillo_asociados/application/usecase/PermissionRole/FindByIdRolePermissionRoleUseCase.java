package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleResponse;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class FindByIdRolePermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public FindByIdRolePermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    @Transactional
    public PermissionRoleResponse execute(int idRole, int page, int size) {

        if (idRole <= 0) {
            throw new IllegalArgumentException("Role id cannot be less than 1");
        }

        Optional<PermissionRoleResponse> permissionRoles = permissionRoleRepository.findByIdRole(idRole, page, size);

        if (permissionRoles.isEmpty()) {
            throw new EmptyListException("No permission roles found for role id " + idRole);
        }

        return permissionRoles.get();
    }

}

