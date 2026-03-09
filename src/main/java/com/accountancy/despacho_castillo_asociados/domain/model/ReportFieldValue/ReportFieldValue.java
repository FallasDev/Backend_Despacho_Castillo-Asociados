package com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;

import java.time.LocalDateTime;

public class ReportFieldValue {

    private int id;
    private String value;
    private ReportField reportField;
    private Report report;
    private LocalDateTime date;
    private boolean active;

    public ReportFieldValue(int id, String value, ReportField reportField, Report report, LocalDateTime date, boolean active) {
        this.id = id;
        this.value = value;
        this.reportField = reportField;
        this.report = report;
        this.date = date;
        this.active = active;
    }

    public ReportFieldValue() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ReportField getReportField() {
        return reportField;
    }

    public void setReportField(ReportField reportField) {
        this.reportField = reportField;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
