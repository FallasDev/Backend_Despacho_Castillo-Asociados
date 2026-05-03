package com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield;

import java.util.List;

public class ServiceCustomFieldDTO {
    private int id;
    private String name;
    private boolean active;
    private ServiceDTO service;
    private List<CustomFieldDTO> customFields;

    public ServiceCustomFieldDTO(int id, String name, boolean active, ServiceDTO service, List<CustomFieldDTO> customFields) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.service = service;
        this.customFields = customFields;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }
    public ServiceDTO getService() { return service; }
    public List<CustomFieldDTO> getCustomFields() { return customFields; }
}

