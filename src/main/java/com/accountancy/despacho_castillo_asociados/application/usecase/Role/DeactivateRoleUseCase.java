package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactivateRoleUseCase {

    private final RoleRepository roleRepository;

    public DeactivateRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void execute(int id) {
        boolean result = roleRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate Role with id " + id);
        }
    }

}

