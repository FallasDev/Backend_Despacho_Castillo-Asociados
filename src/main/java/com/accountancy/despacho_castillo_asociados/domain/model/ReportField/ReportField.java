package com.accountancy.despacho_castillo_asociados.domain.model.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;

public class ReportField {

    private int id;
    private String label;
    private int order;
    private String placeholder;
    private String helpText;
    private String defaultValue;
    private boolean multiple;
    private Type type;
    private boolean active;
    private ReportCategory reportCategory;

    public ReportField(int id, String label, int order, String placeholder, String helpText, String defaultValue, boolean multiple, boolean active, Type type, ReportCategory reportCategory) {
         this.id = id;
        this.id = id;
        this.label = label;
        this.active = active;
        this.order = order;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.defaultValue = defaultValue;
        this.multiple = multiple;
        this.type = type;
        this.reportCategory = reportCategory;
    }

    public ReportField() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ReportCategory getReportCategory() {
        return reportCategory;
    }

    public void setReportCategory(ReportCategory reportCategory) {
        this.reportCategory = reportCategory;
    }
}
