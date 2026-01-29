package com.accountancy.despacho_castillo_asociados.domain.model.Report;

import java.net.URL;
import java.time.LocalDate;

public class DomainReport {

    private int id;
    private String title;
    private String description;
    private URL image;
    private String category;
    private LocalDate date;
    private boolean active;

    public DomainReport(int id, String title, String description, URL image, String category, LocalDate date, boolean active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.date = date;
        this.active = active;
    }

    public DomainReport(){

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
        description = description;
    }

    public URL getImage() {
        return image;
    }

    public void setImage(URL image) {
        this.image = image;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
