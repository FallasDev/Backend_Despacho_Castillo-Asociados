package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdRoleUseCase {

    private final RoleRepository roleRepository;

    public FindByIdRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role execute(int id) {
        Optional<Role> role = roleRepository.findById(id);

        if (role.isEmpty() || !role.get().isActive()) {
            throw new EntityNotFoundException("Role with id " + id + " not found");
        }

        return role.get();
    }

}

