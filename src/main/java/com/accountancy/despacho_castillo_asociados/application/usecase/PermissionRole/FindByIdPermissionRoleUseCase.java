package com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdPermissionRoleUseCase {

    private final PermissionRoleRepository permissionRoleRepository;

    public FindByIdPermissionRoleUseCase(PermissionRoleRepository permissionRoleRepository) {
        this.permissionRoleRepository = permissionRoleRepository;
    }

    public PermissionRole execute(int id) {
        Optional<PermissionRole> permissionRole = permissionRoleRepository.findById(id);

        if (permissionRole.isEmpty()) {
            throw new EntityNotFoundException("PermissionRole with id " + id + " not found");
        }

        return permissionRole.get();
    }

}

