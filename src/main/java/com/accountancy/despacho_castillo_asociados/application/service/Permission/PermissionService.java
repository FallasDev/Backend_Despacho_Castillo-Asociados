package com.accountancy.despacho_castillo_asociados.application.service.Permission;

import com.accountancy.despacho_castillo_asociados.application.usecase.Permission.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.Permission.PermissionRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final FindAllPermissionUseCase findAllPermissionUseCase;
    private final FindByIdPermissionUseCase findByIdPermissionUseCase;
    private final FindByNamePermissionUseCase findByNamePermissionUseCase;
    private final FindByDescriptionPermissionUseCase findByDescriptionPermissionUseCase;
    private final UpdatePermissionUseCase updatePermissionUseCase;

    public PermissionService(
            FindAllPermissionUseCase findAllPermissionUseCase,
            FindByIdPermissionUseCase findByIdPermissionUseCase,
            FindByNamePermissionUseCase findByNamePermissionUseCase,
            FindByDescriptionPermissionUseCase findByDescriptionPermissionUseCase,
            UpdatePermissionUseCase updatePermissionUseCase
    ) {
        this.findAllPermissionUseCase = findAllPermissionUseCase;
        this.findByIdPermissionUseCase = findByIdPermissionUseCase;
        this.findByNamePermissionUseCase = findByNamePermissionUseCase;
        this.findByDescriptionPermissionUseCase = findByDescriptionPermissionUseCase;
        this.updatePermissionUseCase = updatePermissionUseCase;
    }

    public PageResult<Permission> findAllPermissions(int page, int size) {
        return findAllPermissionUseCase.execute(page, size);
    }

    public Permission findByIdPermission(int id) {
        return findByIdPermissionUseCase.execute(id);
    }

    public Permission findByNamePermission(String name) {
        return findByNamePermissionUseCase.execute(name);
    }

    public Permission findByDescriptionPermission(String description) {
        return findByDescriptionPermissionUseCase.execute(description);
    }

    public Permission updatePermission(PermissionRequest permissionRequest, int id) {
        return updatePermissionUseCase.execute(permissionRequest, id);
    }

}

