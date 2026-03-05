package com.accountancy.despacho_castillo_asociados.domain.model.Service;

public class ServiceRequest {

    private String name;
    private String description;

    public ServiceRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
