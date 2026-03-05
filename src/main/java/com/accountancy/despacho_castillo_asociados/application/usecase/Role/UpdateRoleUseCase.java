package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class UpdateRoleUseCase {

    private final RoleRepository roleRepository;

    public UpdateRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role execute(RoleRequest roleRequest, int id) {

        if (roleRequest == null) {
            throw new BadRequestException("Role cannot be null");
        }

        if (roleRequest.getName() == null || roleRequest.getName().isEmpty()) {
            throw new BadRequestException("Role name cannot be null or empty");
        }

        Optional<Role> existingRole = roleRepository.findById(id);

        if (existingRole.isEmpty()) {
            throw new EntityNotFoundException("Role with id " + id + " does not exist");
        }

        if (!existingRole.get().isActive()) {
            throw new BadRequestException("Role with id " + id + " is not active");
        }

        Role updatedRole = roleRepository.Update(roleRequest, id);

        if (updatedRole == null) {
            throw new BadRequestException("Failed to update Role with id " + id);
        }

        return updatedRole;
    }

}

