package com.accountancy.despacho_castillo_asociados.domain.model.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public class CustomFieldBuilder implements Builder {

    private CustomField customField;

    public CustomFieldBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.customField = new CustomField();
    }

    @Override
    public CustomFieldBuilder setId(int id) {
        this.customField.setId(id);
        return this;
    }

    @Override
    public CustomFieldBuilder setName(String name) {
        this.customField.setName(name);
        return this;
    }

    @Override
    public CustomFieldBuilder setIsRequired(boolean isRequired) {
        this.customField.setRequired(isRequired);
        return this;
    }

    @Override
    public CustomFieldBuilder setIsActive(boolean isActive) {
        this.customField.setActive(isActive);
        return this;
    }

    @Override
    public CustomFieldBuilder setIsExclusive(boolean isExclusive) {
        this.customField.setExclusive(isExclusive);
        return this;
    }

    @Override
    public CustomFieldBuilder setType(Type type) {
        this.customField.setType(type);
        return this;
    }

    @Override
    public CustomFieldBuilder setPlaceholder(String placeholder) {
        this.customField.setPlaceholder(placeholder);
        return this;
    }

    @Override
    public CustomFieldBuilder setHelpText(String helpText) {
        this.customField.setHelpText(helpText);
        return this;
    }

    @Override
    public CustomFieldBuilder setDefaultValue(String defaultValue) {
        this.customField.setDefaultValue(defaultValue);
        return this;
    }

    @Override
    public CustomField getResult() {
        return this.customField;
    }
}
