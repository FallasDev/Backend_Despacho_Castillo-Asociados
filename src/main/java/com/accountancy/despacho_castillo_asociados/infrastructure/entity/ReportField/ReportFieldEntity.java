package com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportField;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportCategory.ReportCategoryEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "report_fields")
public class ReportFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String label;

    @Column(nullable = false)
    private int field_order;

    @Column(nullable = false)
    private String placeholder;

    @Column(nullable = false)
    private String helpText;

    @Column(nullable = false)
    private String defaultValue;

    @Column(nullable = false)
    private boolean multiple;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private int typeId;

    @Column(nullable = false)
    private int reportCategoryId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "typeId", insertable = false, updatable = false)
    private TypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reportCategoryId", insertable = false, updatable = false)
    private ReportCategoryEntity reportCategory;

    public ReportFieldEntity(int id, String label, int field_order, String placeholder, String helpText, String defaultValue, boolean multiple, boolean active) {
        this.id = id;
        this.label = label;
        this.field_order = field_order;
        this.placeholder = placeholder;
        this.helpText = helpText;
        this.defaultValue = defaultValue;
        this.multiple = multiple;
        this.active = active;
    }

    public ReportFieldEntity() {
        
    }
}
