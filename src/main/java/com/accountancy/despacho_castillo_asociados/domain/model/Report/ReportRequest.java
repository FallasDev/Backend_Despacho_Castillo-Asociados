package com.accountancy.despacho_castillo_asociados.domain.model.Report;

import java.net.URL;
import java.time.LocalDate;

public class ReportRequest {

    private String title;
    private String description;
    private URL image;
    private int categoryId;

    public ReportRequest(String title, String description, URL image, int categoryId, LocalDate date) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
