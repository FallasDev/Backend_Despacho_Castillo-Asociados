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
    private String placeholder;
    private String helpText;
    private String defaultValue;
    private Type type;


    // Exclusive is pending implementation
    public CustomField(int id, String name, boolean isRequired, boolean isActive, boolean isExclusive, String placeholder, String helpText, String defaultValue, Type type) {
        this.id = id;
        this.name = name;
        this.isRequired = isRequired;
        this.isActive = isActive;
        this.isExclusive = isExclusive;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.defaultValue = defaultValue;
        this.type = type;
    }

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }



    public boolean isExclusive() {
        return isExclusive;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
