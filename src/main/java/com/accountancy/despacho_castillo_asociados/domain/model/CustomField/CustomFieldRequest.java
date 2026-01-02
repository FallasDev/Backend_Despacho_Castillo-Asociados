package com.accountancy.despacho_castillo_asociados.domain.model.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public class CustomFieldRequest {


    private String name;
    private boolean isRequired;
    private boolean isExclusive;
    private int typeId;

    public CustomFieldRequest(String name, boolean isRequired, boolean isExclusive, int typeId) {
        this.name = name;
        this.isRequired = isRequired;
        this.isExclusive = isExclusive;
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean isExclusive) {
        this.isExclusive = isExclusive;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }




}
