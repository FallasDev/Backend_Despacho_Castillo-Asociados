package com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;

public class ServiceCustomField {

    private int id;
    private DomainService service;
    private CustomField customField;
    private boolean isActive;

    public ServiceCustomField(int id, DomainService service, CustomField customField, boolean isActive) {
        this.id = id;
        this.service = service;
        this.customField = customField;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DomainService getService() {
        return service;
    }

    public void setService(DomainService service) {
        this.service = service;
    }

    public CustomField getCustomField() {
        return customField;
    }

    public void setCustomField(CustomField customField) {
        this.customField = customField;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
