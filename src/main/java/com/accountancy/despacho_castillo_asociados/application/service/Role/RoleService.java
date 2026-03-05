package com.accountancy.despacho_castillo_asociados.application.service.Role;

import com.accountancy.despacho_castillo_asociados.application.usecase.Role.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final CreateRoleUseCase createRoleUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final DeactivateRoleUseCase deactivateRoleUseCase;
    private final FindAllRoleUseCase findAllRoleUseCase;
    private final FindByIdRoleUseCase findByIdRoleUseCase;
    private final FindByNameRoleUseCase findByNameRoleUseCase;

    public RoleService(
            CreateRoleUseCase createRoleUseCase,
            UpdateRoleUseCase updateRoleUseCase,
            DeactivateRoleUseCase deactivateRoleUseCase,
            FindAllRoleUseCase findAllRoleUseCase,
            FindByIdRoleUseCase findByIdRoleUseCase,
            FindByNameRoleUseCase findByNameRoleUseCase
    ) {
        this.createRoleUseCase = createRoleUseCase;
        this.updateRoleUseCase = updateRoleUseCase;
        this.deactivateRoleUseCase = deactivateRoleUseCase;
        this.findAllRoleUseCase = findAllRoleUseCase;
        this.findByIdRoleUseCase = findByIdRoleUseCase;
        this.findByNameRoleUseCase = findByNameRoleUseCase;
    }

    public Role createRole(RoleRequest roleRequest) {
        return createRoleUseCase.execute(roleRequest);
    }

    public Role updateRole(RoleRequest roleRequest, int id) {
        return updateRoleUseCase.execute(roleRequest, id);
    }

    public void deactivateRole(int id) {
        deactivateRoleUseCase.execute(id);
    }

    public PageResult<Role> findAllRoles(int page, int size) {
        return findAllRoleUseCase.execute(page, size);
    }

    public Role findByIdRole(int id) {
        return findByIdRoleUseCase.execute(id);
    }

    public Role findByNameRole(String name) {
        return findByNameRoleUseCase.execute(name);
    }

}

