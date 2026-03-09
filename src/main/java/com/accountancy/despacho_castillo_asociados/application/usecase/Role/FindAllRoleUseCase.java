package com.accountancy.despacho_castillo_asociados.application.usecase.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindAllRoleUseCase {

    private final RoleRepository roleRepository;

    public FindAllRoleUseCase(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public PageResult<Role> execute(int page, int size) {

        PageResult<Role> roles = roleRepository.findAll(page, size);

        if (roles.content().isEmpty()) {
            throw new EmptyListException("No roles found");
        }

        return roles;
    }

}

