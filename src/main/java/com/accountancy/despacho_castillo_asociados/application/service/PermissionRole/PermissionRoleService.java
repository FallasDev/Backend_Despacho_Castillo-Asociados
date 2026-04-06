package com.accountancy.despacho_castillo_asociados.application.service.PermissionRole;

import com.accountancy.despacho_castillo_asociados.application.usecase.PermissionRole.*;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class PermissionRoleService {

    private final CreatePermissionRoleUseCase createPermissionRoleUseCase;
    private final DeletePermissionRoleUseCase deletePermissionRoleUseCase;
    private final FindAllPermissionRoleUseCase findAllPermissionRoleUseCase;
    private final FindByIdPermissionRoleUseCase findByIdPermissionRoleUseCase;
    private final FindByIdRolePermissionRoleUseCase findByIdRolePermissionRoleUseCase;
    private final FindByPermissionIdPermissionRoleUseCase findByPermissionIdPermissionRoleUseCase;

    public PermissionRoleService(
            CreatePermissionRoleUseCase createPermissionRoleUseCase,
            DeletePermissionRoleUseCase deletePermissionRoleUseCase,
            FindAllPermissionRoleUseCase findAllPermissionRoleUseCase,
            FindByIdPermissionRoleUseCase findByIdPermissionRoleUseCase,
            FindByIdRolePermissionRoleUseCase findByIdRolePermissionRoleUseCase,
            FindByPermissionIdPermissionRoleUseCase findByPermissionIdPermissionRoleUseCase
    ) {
        this.createPermissionRoleUseCase = createPermissionRoleUseCase;
        this.deletePermissionRoleUseCase = deletePermissionRoleUseCase;
        this.findAllPermissionRoleUseCase = findAllPermissionRoleUseCase;
        this.findByIdPermissionRoleUseCase = findByIdPermissionRoleUseCase;
        this.findByIdRolePermissionRoleUseCase = findByIdRolePermissionRoleUseCase;
        this.findByPermissionIdPermissionRoleUseCase = findByPermissionIdPermissionRoleUseCase;
    }

    public PermissionRole createPermissionRole(PermissionRoleRequest permissionRoleRequest) {
        return createPermissionRoleUseCase.execute(permissionRoleRequest);
    }

    public void deletePermissionRole(int id) {
        deletePermissionRoleUseCase.execute(id);
    }

    public PageResult<PermissionRole> findAllPermissionRoles(int page, int size) {
        return findAllPermissionRoleUseCase.execute(page, size);
    }

    public PermissionRole findByIdPermissionRole(int id) {
        return findByIdPermissionRoleUseCase.execute(id);
    }

    public PermissionRoleResponse findByIdRolePermissionRole(int idRole, int page, int size) {
        return findByIdRolePermissionRoleUseCase.execute(idRole, page, size);
    }

    public PageResult<PermissionRole> findByPermissionIdPermissionRole(int permissionId, int page, int size) {
        return findByPermissionIdPermissionRoleUseCase.execute(permissionId, page, size);
    }

}

