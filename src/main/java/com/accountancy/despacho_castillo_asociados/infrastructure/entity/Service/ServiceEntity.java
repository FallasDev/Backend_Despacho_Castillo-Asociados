package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services")
public class    ServiceEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String name;

        @Column(nullable = false)
        private String description;

        @Column(nullable = false)
        private boolean active;

        public ServiceEntity() {
        }

        public ServiceEntity(String name, String description, boolean active) {
            this.name = name;
            this.description = description;
            this.active = active;
        }

        public ServiceEntity(int id, String name, String description, boolean active) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.active = active;
        }


}
