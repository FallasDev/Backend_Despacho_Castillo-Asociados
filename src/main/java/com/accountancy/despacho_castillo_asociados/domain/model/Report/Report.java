package com.accountancy.despacho_castillo_asociados.domain.model.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;

import java.net.URL;
import java.time.LocalDate;

public class Report {

    private int id;
    private String title;
    private String description;
    private URL image;
    private ReportCategory category;
    private LocalDate date;
    private boolean active;

    public Report(int id, String title, String description, URL image, ReportCategory category, LocalDate date, boolean active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.date = date;
        this.active = active;
    }

    public Report() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
    }

    public ReportCategory getCategory() {
        return category;
    }

    public void setCategory(ReportCategory category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
