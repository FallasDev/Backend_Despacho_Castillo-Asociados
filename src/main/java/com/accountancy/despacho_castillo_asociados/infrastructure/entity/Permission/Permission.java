package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

        public Permission() {
        }

        public Permission(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Permission(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

}
