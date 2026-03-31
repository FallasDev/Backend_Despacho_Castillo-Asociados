package com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "custom_fields")
@Entity
public class CustomFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "required", nullable = false)
    private boolean required = false;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @Column(name = "exclusive", nullable = false)
    private boolean exclusive = false;

    @Column(name = "placeholder", length = 255)
    private String placeholder = "";

    @Column(name = "help_text", length = 255)
    private String helpText = "";

    @Column(name = "default_value", length = 255)
    private String defaultValue = "";

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private TypeEntity type;

    public CustomFieldEntity() {

    }

    public CustomFieldEntity(String name, boolean required, boolean active, boolean exclusive, TypeEntity type) {
        this.name = name;
        this.required = required;
        this.active = active;
        this.exclusive = exclusive;
        this.type = type;
    }



}
