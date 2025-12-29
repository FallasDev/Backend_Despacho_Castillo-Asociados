package com.accountancy.despacho_castillo_asociados.domain.model.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public interface Builder {

    void reset();
    CustomFieldBuilder setId(int id);
    CustomFieldBuilder setName(String name);
    CustomFieldBuilder setIsRequired(boolean isRequired);
    CustomFieldBuilder setIsActive(boolean isActive);
    CustomFieldBuilder setIsExclusive(boolean isExclusive);
    CustomFieldBuilder setType(Type type);
    CustomField getResult();

}
