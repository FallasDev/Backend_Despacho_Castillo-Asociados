package com.accountancy.despacho_castillo_asociados.domain.model.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;

import java.util.ArrayList;

public class RoleRequest {

    private String name;
    private String description;

    public RoleRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RoleRequest(){}

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

}
