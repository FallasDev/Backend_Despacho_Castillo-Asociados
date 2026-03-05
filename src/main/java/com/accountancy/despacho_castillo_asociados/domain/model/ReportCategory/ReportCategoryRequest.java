package com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory;

import java.time.LocalDate;

public class ReportCategoryRequest {

    private String name;

    public ReportCategoryRequest(String name, LocalDate date) {
        this.name = name;
    }

    public ReportCategoryRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
