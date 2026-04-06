package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

public class FormalitieCustomFieldWithValueUpdate {

    private int id;
    private int customFieldId;
    private String value;

    public FormalitieCustomFieldWithValueUpdate(int id,int customFieldId, String value) {
        this.id = id;
        this.customFieldId = customFieldId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
