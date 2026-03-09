package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;


public class CreateRoleUseCase {

    private final RoleRepository roleRepository;

    public CreateRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role execute(RoleRequest roleRequest) {

        if (roleRequest == null) {
            throw new BadRequestException("Role cannot be null");
        }

        if (roleRequest.getName() == null || roleRequest.getName().isEmpty()) {
            throw new BadRequestException("Role name cannot be null or empty");
        }

        Optional<Role> existingRole = roleRepository.findByNameAndIsActive(roleRequest.getName());

        if (existingRole.isPresent()) {
            throw new BadRequestException("Role with name " + roleRequest.getName() + " already exists");
        }

        Optional<Role> inactiveRole = roleRepository.findByNameAndIsInactive(roleRequest.getName());

        if (inactiveRole.isPresent()) {
            Role reactivatedRole = inactiveRole.get();
            roleRepository.activate(reactivatedRole.getId());
            return reactivatedRole;
        }

        return roleRepository.create(roleRequest);
    }

}

