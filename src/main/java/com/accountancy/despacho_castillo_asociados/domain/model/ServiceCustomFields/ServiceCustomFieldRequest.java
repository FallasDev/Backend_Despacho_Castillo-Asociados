package com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields;

public class ServiceCustomFieldRequest {

    private int serviceId;
    private int customFieldId;

    public ServiceCustomFieldRequest(int serviceId, int customFieldId) {
        this.serviceId = serviceId;
        this.customFieldId = customFieldId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(int customFieldId) {
        this.customFieldId = customFieldId;
    }

}
