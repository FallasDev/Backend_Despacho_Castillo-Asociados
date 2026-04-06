package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

public class FormalitieCustomFieldWithValue {

    private int customFieldId;
    private String value;

    public FormalitieCustomFieldWithValue(int customFieldId, String value) {
        this.customFieldId = customFieldId;
        this.value = value;
    }

    public int getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(int customFieldId) {
        this.customFieldId = customFieldId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
