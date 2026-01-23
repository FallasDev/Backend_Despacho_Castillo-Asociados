package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;

public class FormalitieCustomField {

    private int id;
    private Formalitie formalitie;
    private CustomField customField;
    private String value;
    private boolean active;

    public FormalitieCustomField(int id, Formalitie formalitie, CustomField customField, String value, boolean active) {
        this.id = id;
        this.formalitie = formalitie;
        this.customField = customField;
        this.value = value;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public Formalitie getFormalitie() {
        return formalitie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFormalitie(Formalitie formalitie) {
        this.formalitie = formalitie;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
