package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "formalities")
public class FormalitieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "state", nullable = false)
    private int state;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "active", nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity service;

    // Client and user pending implementation


}
