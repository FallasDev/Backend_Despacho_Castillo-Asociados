package com.accountancy.despacho_castillo_asociados.domain.model.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;

import java.util.ArrayList;

public class Role {
    private int id;
    private String name;
    private String description;
    private ArrayList<PermissionRole> permissions;
    private boolean active;

    public Role(int id, String name, String description, ArrayList<PermissionRole> permissions, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions;
        this.active = active;
    }

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PermissionRole> getPermissions() {
        return permissions;
    }

    public void setPermissions(ArrayList<PermissionRole> permissions) {
        this.permissions = permissions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}