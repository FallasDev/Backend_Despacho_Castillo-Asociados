package com.accountancy.despacho_castillo_asociados.domain.repository.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface PermissionRepository {

    Permission update(Permission permission, int id);

    Optional< Permission> findById(int id);
    Optional< Permission> fintByName(String name);
    Optional<Permission> fintByDescription(String description);
    PageResult<Permission> findAll(int page, int size);
}
