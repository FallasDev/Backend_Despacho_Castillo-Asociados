package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByNameRoleUseCase {

    private final RoleRepository roleRepository;

    public FindByNameRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role execute(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be null or empty");
        }

        Optional<Role> role = roleRepository.findByNameAndIsActive(name);

        if (role.isEmpty()) {
            throw new EntityNotFoundException("Role with name " + name + " not found");
        }

        return role.get();
    }

}

