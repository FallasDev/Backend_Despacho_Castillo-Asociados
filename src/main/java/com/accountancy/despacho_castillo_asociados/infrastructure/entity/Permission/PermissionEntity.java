package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    public PermissionEntity() {}

    public PermissionEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public PermissionEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

