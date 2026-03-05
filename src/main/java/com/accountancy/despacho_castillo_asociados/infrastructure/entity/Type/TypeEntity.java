package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "types")
public class TypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    @OneToMany
    @JoinColumn(name = "type_id")
    private List<CustomFieldEntity> customFields;

    public TypeEntity(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public TypeEntity(int id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public TypeEntity() {

    }



}
