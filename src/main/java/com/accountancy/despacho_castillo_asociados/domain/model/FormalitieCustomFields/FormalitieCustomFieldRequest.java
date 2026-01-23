package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;

public class FormalitieCustomFieldRequest {

    private int formalitieId;
    private int customFieldId;
    private String value;

    public FormalitieCustomFieldRequest(int formalitieId, int customFieldId, String value) {
        this.formalitieId = formalitieId;
        this.customFieldId = customFieldId;
        this.value = value;
    }

    public int getFormalitieId() {
        return formalitieId;
    }

    public void setFormalitieId(int formalitieId) {
        this.formalitieId = formalitieId;
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
