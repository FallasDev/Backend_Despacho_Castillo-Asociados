package com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface PermissionRoleRepository {

    PermissionRole create(PermissionRoleRequest permissionRole);
    PermissionRole update(PermissionRoleRequest permissionRole, int id);
    boolean delete(int id);

    Optional<PermissionRole> findById(int id);
    PageResult<PermissionRole> findAll(int page, int size);
    Optional<PermissionRoleResponse> findByIdRole(int idRole, int page, int size);
    PageResult<PermissionRole> findByPermissionId(int permissionId, int page, int size);
}
