package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Report;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor // Genera el constructor vacío automáticamente
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
    private String image; // Cambiado de URL a String

    @Column(nullable = false)
    private int categoryId; // Cambiado de ReportCategoryEntity a int

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "categoryId", insertable = false, updatable = false)
    private ReportCategoryEntity category;

    public ReportEntity(int id, String title, String description, String image, int categoryId, LocalDate date, boolean active, ReportCategoryEntity category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.categoryId = categoryId;
        this.date = date;
        this.active = active;
        this.category = category;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public ReportCategoryEntity getCategory() {
        return category;
    }

    public void setCategory(ReportCategoryEntity category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", categoryId=" + categoryId +
                ", date=" + date +
                ", active=" + active +
                ", category=" + category.getName() +
                '}';
    }
}
