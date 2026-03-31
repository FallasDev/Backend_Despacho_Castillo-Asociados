package com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields;

import java.util.List;

public class CustomFieldWithOrder {

    private int customFieldId;
    private int order;

    public CustomFieldWithOrder(int customFieldId, int order) {
        this.customFieldId = customFieldId;
        this.order = order;
    }

    public int getCustomFieldId() {
        return customFieldId;
    }

    public void setCustomFieldId(int customFieldId) {
        this.customFieldId = customFieldId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
