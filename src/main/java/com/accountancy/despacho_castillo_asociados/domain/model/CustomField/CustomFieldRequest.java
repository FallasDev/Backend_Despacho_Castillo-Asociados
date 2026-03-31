package com.accountancy.despacho_castillo_asociados.domain.model.CustomField;

public class CustomFieldRequest {


    private String name;
    private boolean isRequired;
    private boolean isExclusive;
    private int typeId;
    private String placeholder;
    private String helpText;
    private String defaultValue;

    public CustomFieldRequest(String name, boolean isRequired, boolean isExclusive, int typeId) {
        this.name = name;
        this.isRequired = isRequired;
        this.isExclusive = isExclusive;
        this.typeId = typeId;
    }

    public CustomFieldRequest(String name, boolean isRequired, boolean isExclusive, int typeId, String placeholder, String helpText, String defaultValue) {
        this.name = name;
        this.isRequired = isRequired;
        this.isExclusive = isExclusive;
        this.typeId = typeId;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.defaultValue = defaultValue;
    }

    public CustomFieldRequest() {
        this.name = "";
        this.isRequired = false;
        this.isExclusive = false;
        this.typeId = 0;
        this.placeholder = "";
        this.helpText = "";
        this.defaultValue = "";
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


    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
