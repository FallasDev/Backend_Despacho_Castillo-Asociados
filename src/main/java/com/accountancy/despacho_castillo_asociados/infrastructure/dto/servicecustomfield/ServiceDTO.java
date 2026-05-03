package com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield;

public class ServiceDTO {
    private int id;
    private String name;
    private String description;
    private boolean active;

    public ServiceDTO(int id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public String getDescription(){ return description; }
    public boolean isActive(){ return active; }
}

