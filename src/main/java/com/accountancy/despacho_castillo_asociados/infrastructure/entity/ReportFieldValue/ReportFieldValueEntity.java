package com.accountancy.despacho_castillo_asociados.infrastructure.entity.ReportFieldValue;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "report_field_values")
public class ReportFieldValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private int reportId;

    @Column(nullable = false)
    private int reportFieldId;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(nullable = false)
    private boolean active = true;

    public ReportFieldValueEntity() {
    }

    public ReportFieldValueEntity(int id, int reportId, int reportFieldId, String value) {
        this.id = id;
        this.reportId = reportId;
        this.reportFieldId = reportFieldId;
        this.value = value;
    }
}
