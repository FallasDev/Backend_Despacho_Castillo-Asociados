package com.accountancy.despacho_castillo_asociados.domain.model.Report;

import java.net.URL;
import java.time.LocalDate;

public class ServiceRequest {

    private String title;
    private String description;
    private URL image;
    private String category;
    private LocalDate date;

    public ServiceRequest(String title, String description, URL image, String category, LocalDate date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.date = date;
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
