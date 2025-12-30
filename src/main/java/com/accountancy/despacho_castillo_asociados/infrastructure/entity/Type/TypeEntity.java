package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
