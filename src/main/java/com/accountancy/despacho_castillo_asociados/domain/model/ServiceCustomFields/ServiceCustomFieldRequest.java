package com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields;

import java.util.List;

public class ServiceCustomFieldRequest {

    private int serviceId;
    private String name;
    private List<CustomFieldWithOrder> customFieldIds;

    public ServiceCustomFieldRequest(List<CustomFieldWithOrder> customFieldIds, int serviceId, String name) {
        this.name = name;
        this.customFieldIds = customFieldIds;
        this.serviceId = serviceId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public List<CustomFieldWithOrder> getCustomFieldIds() {
        return customFieldIds;
    }

    public void setCustomFieldIds(List<CustomFieldWithOrder> customFieldIds) {
        this.customFieldIds = customFieldIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServiceCustomFieldRequest{" +
                "serviceId=" + serviceId +
                ", name='" + name + '\'' +
                ", customFieldIds=" + customFieldIds.stream().map(
                        cfo -> "CustomFieldWithOrder{customFieldId=" + cfo.getCustomFieldId() + ", order=" + cfo.getOrder() + "}"
                ).toList(
        ) +
                '}';
    }
}
