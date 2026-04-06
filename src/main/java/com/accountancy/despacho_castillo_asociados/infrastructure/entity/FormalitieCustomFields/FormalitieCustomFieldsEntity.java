package com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie.FormalitieEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "formalities_custom_fields")
public class FormalitieCustomFieldsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formalitie_id")
    private FormalitieEntity formalitie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "custom_field_id")
    private CustomFieldEntity customField;

    public FormalitieCustomFieldsEntity() {
    }

    public FormalitieCustomFieldsEntity(CustomFieldEntity customField, FormalitieEntity formalitie, boolean active, String value) {
        this.customField = customField;
        this.formalitie = formalitie;
        this.active = active;
        this.value = value;
    }

    public FormalitieCustomFieldsEntity(int id, String value, boolean active, FormalitieEntity formalitie, CustomFieldEntity customField) {
        this.id = id;
        this.value = value;
        this.active = active;
        this.formalitie = formalitie;
        this.customField = customField;
    }
}
