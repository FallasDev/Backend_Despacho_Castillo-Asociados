package com.accountancy.despacho_castillo_asociados.domain.model.ReportCategories;

import java.time.LocalDate;

public class DomainService {

    private int id;
    private String category;
    private LocalDate date;
    private boolean active;
    private int visibility;

    public DomainService(int id, String category, LocalDate date, boolean active, int visibility){
        this.id = id;
        this.category = category;
        this.date = date;
        this.active = active;
        this.visibility = visibility;
    }

    public DomainService(){

    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
