package com.accountancy.despacho_castillo_asociados.application.usecase.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindAllPermissionUseCase {

    private final PermissionRepository permissionRepository;

    public FindAllPermissionUseCase(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public PageResult<Permission> execute(int page, int size) {

        PageResult<Permission> permissions = permissionRepository.findAll(page, size);

        if (permissions.content().isEmpty()) {
            throw new EmptyListException("No permissions found");
        }

        return permissions;
    }

}

