package com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "services")
public class ReportCategoryEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String category;

        @Column(nullable = false)
        private LocalDate date;

        @Column(nullable = false)
        private boolean active;

        @Column(nullable = false)
        private int visibility;

    public ReportCategoryEntity(int id, String category, LocalDate date, boolean active, int visibility) {
        this.id = id;
        this.category = category;
        this.date = date;
        this.active = active;
        this.visibility = visibility;
    }

    public ReportCategoryEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }
}
