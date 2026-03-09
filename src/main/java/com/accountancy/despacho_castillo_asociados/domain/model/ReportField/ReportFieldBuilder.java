package com.accountancy.despacho_castillo_asociados.domain.model.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public class ReportFieldBuilder implements Builder {

    private ReportField reportField;

    public ReportFieldBuilder() {
        this.reportField = new ReportField();
    }

    @Override
    public void reset() {
        this.reportField = new ReportField();
    }

    public ReportFieldBuilder setId(int id) {
        this.reportField.setId(id);
        return this;
    }

    public ReportFieldBuilder setLabel(String label) {
        this.reportField.setLabel(label);
        return this;
    }

    public ReportFieldBuilder setOrder(int order) {
        this.reportField.setOrder(order);
        return this;
    }

    public ReportFieldBuilder setPlaceholder(String placeholder) {
        this.reportField.setPlaceholder(placeholder);
        return this;
    }

    public ReportFieldBuilder setHelpText(String helpText) {
        this.reportField.setHelpText(helpText);
        return this;
    }

    public ReportFieldBuilder setDefaultValue(String defaultValue) {
        this.reportField.setDefaultValue(defaultValue);
        return this;
    }

    public ReportFieldBuilder setMultiple(boolean multiple) {
        this.reportField.setMultiple(multiple);
        return this;
    }

    @Override
    public ReportFieldBuilder setActive(boolean active) {
        this.reportField.setActive(active);
        return this;
    }

    @Override
    public ReportFieldBuilder setType(Type type) {
        this.reportField.setType(type);
        return this;
    }

    @Override
    public ReportFieldBuilder setReportCategory(ReportCategory reportCategory) {
        this.reportField.setReportCategory(reportCategory);
        return this;
    }

    public ReportField getResult() {
        return this.reportField;
    }

}
