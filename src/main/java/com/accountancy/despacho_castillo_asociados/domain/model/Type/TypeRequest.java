package com.accountancy.despacho_castillo_asociados.domain.model.Type;

public class TypeRequest {

    private String name;

    public TypeRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
