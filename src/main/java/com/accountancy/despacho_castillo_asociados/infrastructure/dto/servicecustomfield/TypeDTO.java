package com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield;

public class TypeDTO {
    private int id;
    private String name;
    private boolean active;

    public TypeDTO(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public int getId(){ return id; }
    public String getName(){ return name; }
    public boolean isActive(){ return active; }
}

