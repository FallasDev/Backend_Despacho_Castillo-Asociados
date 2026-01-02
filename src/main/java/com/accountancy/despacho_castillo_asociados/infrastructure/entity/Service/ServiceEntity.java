package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services")
public class ServiceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String name;

        @Column(nullable = false)
        private String description;

        @Column(nullable = false)
        private boolean active;


}
