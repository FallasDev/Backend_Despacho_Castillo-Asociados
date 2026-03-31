package com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;

import java.util.List;

public class ServiceCustomField {

    private int id;
    private String name;
    private DomainService service;
    private List<CustomField> customFields;
    private CustomField customField;
    private boolean isActive;

    public ServiceCustomField(int id, String name ,DomainService service, List<CustomField> customFields,boolean isActive) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.customFields = customFields;
        this.isActive = isActive;
        this.customField = null;
    }

    public ServiceCustomField(int id, String name ,DomainService service, CustomField customField ,boolean isActive) {
        this.id = id;
        this.name = name;
        this.service = service;
        this.customField = customField;
        this.isActive = isActive;
        this.customFields = null;
    }


    public ServiceCustomField() {}

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

    public DomainService getService() {
        return service;
    }

    public void setService(DomainService service) {
        this.service = service;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
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
