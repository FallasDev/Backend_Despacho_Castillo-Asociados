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

    @Column(name = "formalitie_id", nullable = false)
    private int formalitieId;

    @Column(name = "custom_field_id", nullable = false)
    private int customFieldId;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private boolean active;


}
