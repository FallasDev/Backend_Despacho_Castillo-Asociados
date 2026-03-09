package com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue;

public class ReportFieldValueRequest {

    private int reportFieldId;
    private int reportId;
    private String value;

    public ReportFieldValueRequest(int reportFieldId, int reportId, String value) {
        this.reportFieldId = reportFieldId;
        this.reportId = reportId;
        this.value = value;
    }

    public int getReportFieldId() {
        return reportFieldId;
    }

    public void setReportFieldId(int reportFieldId) {
        this.reportFieldId = reportFieldId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
