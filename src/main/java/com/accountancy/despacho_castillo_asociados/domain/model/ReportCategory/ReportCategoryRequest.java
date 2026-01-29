package com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory;

import java.time.LocalDate;

public class ReportCategoryRequest {

    private String category;
    private LocalDate date;

    public ReportCategoryRequest(String category, LocalDate date) {
        this.category = category;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
