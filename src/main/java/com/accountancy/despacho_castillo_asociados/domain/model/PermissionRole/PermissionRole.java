package com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;

public class PermissionRole {

    private int id;
    private int idRole;
    private Permission permission;
    private Role role;

    public PermissionRole(int id, int idRole, Permission permission) {
        this.id = id;
        this.idRole = idRole;
        this.permission = permission;
    }

    public PermissionRole(int id, int idRole, Permission permission, Role role) {
        this.id = id;
        this.idRole = idRole;
        this.permission = permission;
        this.role = role;
    }

    public PermissionRole(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
