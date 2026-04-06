package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.transaction.Transactional;

public class FindByPermissionIdPermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public FindByPermissionIdPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    @Transactional
    public PageResult<PermissionRole> execute(int permissionId, int page, int size) {

        if (permissionId <= 0) {
            throw new IllegalArgumentException("Permission id cannot be less than 1");
        }

        PageResult<PermissionRole> permissionRoles = permissionRoleRepository.findByPermissionId(permissionId, page, size);

        if (permissionRoles.content().isEmpty()) {
            throw new EmptyListException("No permission roles found for permission id " + permissionId);
        }

        return permissionRoles;
    }

}

