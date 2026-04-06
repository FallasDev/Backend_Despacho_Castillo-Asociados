package com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;

import java.util.List;

public class FormalitieCustomFieldRequest {

    List<FormalitieCustomFieldWithValue> customFields;

    public FormalitieCustomFieldRequest(int formalitieId, List<FormalitieCustomFieldWithValue> customFields) {
        this.customFields = customFields;
    }

    public List<FormalitieCustomFieldWithValue> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<FormalitieCustomFieldWithValue> customFields) {
        this.customFields = customFields;
    }
}
