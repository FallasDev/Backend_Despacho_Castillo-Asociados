package com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;

import java.util.List;

public class PermissionRoleResponse {


    private List<Permission> permissions;
    private Role role;

    public PermissionRoleResponse(List<Permission> permissions, Role role) {
        this.permissions = permissions;
        this.role = role;
    }

    public PermissionRoleResponse(){}

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
