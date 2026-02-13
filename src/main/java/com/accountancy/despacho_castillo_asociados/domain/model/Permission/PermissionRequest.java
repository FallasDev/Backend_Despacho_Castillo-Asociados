package com.accountancy.despacho_castillo_asociados.domain.model.Permission;

public class PermissionRequest {
    private String name;
    private String description;


    public PermissionRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PermissionRequest(){}

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
