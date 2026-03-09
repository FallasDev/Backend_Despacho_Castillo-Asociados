package com.accountancy.despacho_castillo_asociados.domain.model.ReportField;

public class ReportFieldRequest {

    private String label;
    private int order;
    private String placeholder;
    private String helpText;
    private String defaultValue;
    private boolean multiple;
    private int typeId;
    private int reportCategoryId;

    public ReportFieldRequest(String label, int order, String placeholder, String helpText, String defaultValue, boolean multiple, int typeId, int reportCategoryId) {
         this.typeId = typeId;
        this.label = label;
        this.order = order;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.defaultValue = defaultValue;
        this.multiple = multiple;
        this.reportCategoryId = reportCategoryId;
    }

    public ReportFieldRequest() {
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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getReportCategoryId() {
        return reportCategoryId;
    }

    public void setReportCategoryId(int reportCategoryId) {
        this.reportCategoryId = reportCategoryId;
    }
}
