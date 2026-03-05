
package com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;

public class PermissionRoleRequest {

    private int idRole;
    private Permission permission;

    public PermissionRoleRequest(int idRole, Permission permission) {
        this.idRole = idRole;
        this.permission = permission;
    }

    public PermissionRoleRequest() {}

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}

