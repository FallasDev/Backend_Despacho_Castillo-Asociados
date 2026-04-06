package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;

public class FormalitieCustomField {

    private int id;
    private CustomField customField;
    private Formalitie formalitie;
    private String value;
    private boolean active;

    public FormalitieCustomField(int id,CustomField customField, Formalitie formalitie , String value) {
        this.id = id;
        this.customField = customField;
        this.value = value;
        this.formalitie = formalitie;
    }

    public FormalitieCustomField(int id,CustomField customField, Formalitie formalitie , String value, boolean active) {
        this.id = id;
        this.customField = customField;
        this.value = value;
        this.formalitie = formalitie;
        this.active = active;
    }

    public FormalitieCustomField(CustomField customField, Formalitie formalitie, String value) {
        this.customField = customField;
        this.formalitie = formalitie;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    public Formalitie getFormalitie() {
        return formalitie;
    }

    public void setFormalitie(Formalitie formalitie) {
        this.formalitie = formalitie;
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

    @Override
    public String toString() {
        return "FormalitieCustomField{" +
                "id=" + id +
                ", customField=" + customField.getId() +
                ", formalitie=" + formalitie.getId() +
                ", value='" + value + '\'' +
                ", active=" + active +
                '}';
    }
}
