package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Report;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reports")
public class ReportEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String title;

        @Column(nullable = false)
        private String description;

        @Column(nullable = false)
        private URL image;

        @Column(nullable = false)
        private String category;

        @Column(nullable = false)
        private LocalDate date;

        @Column(nullable = false)
        private boolean active;

    public ReportEntity(int id, String title, String description, URL image, String category, LocalDate date, boolean active) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.date = date;
        this.active = active;
    }

    public ReportEntity() {
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
