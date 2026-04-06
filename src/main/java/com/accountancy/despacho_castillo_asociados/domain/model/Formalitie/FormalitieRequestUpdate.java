package com.accountancy.despacho_castillo_asociados.domain.model.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldWithValue;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldWithValueUpdate;

import java.util.List;

public class FormalitieRequestUpdate {

    private int serviceId;
    private int clientId;
    private int userId;
    List<FormalitieCustomFieldWithValueUpdate> customFields;

    public FormalitieRequestUpdate() {
    }

    public FormalitieRequestUpdate(int userId, int clientId, int serviceId) {
        this.userId = userId;
        this.clientId = clientId;
        this.serviceId = serviceId;
    }

    public FormalitieRequestUpdate(int serviceId, int clientId, int userId, List<FormalitieCustomFieldWithValueUpdate> customFields) {
        this.serviceId = serviceId;
        this.clientId = clientId;
        this.userId = userId;
        this.customFields = customFields;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<FormalitieCustomFieldWithValueUpdate> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<FormalitieCustomFieldWithValueUpdate> customFields) {
        this.customFields = customFields;
    }

    @Override
    public String toString() {
        return "FormalitieRequestUpdate{" +
                "serviceId=" + serviceId +
                ", clientId=" + clientId +
                ", userId=" + userId +
                ", customFields=" + customFields.toString() +
                '}';
    }
}
