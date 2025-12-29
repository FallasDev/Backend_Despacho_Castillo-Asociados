package com.accountancy.despacho_castillo_asociados.domain.model.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

public class CustomField {

    private int id;
    private String name;
    private boolean isRequired;
    private boolean isActive;
    private boolean isExclusive;
    private Type type;


    // Exclusive is pending implementation

    public CustomField(int id, String name, boolean isRequired, boolean isActive, boolean isExclusive, Type type) {
        this.id = id;
        this.name = name;
        this.isRequired = isRequired;
        this.isActive = isActive;
        this.isExclusive = isExclusive;
        this.type = type;
    }

    public CustomField() {
        this.id = 0;
        this.name = "";
        this.isRequired = false;
        this.isActive = false;
        this.isExclusive = false;
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

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean isExclusive) {
        this.isExclusive = isExclusive;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
