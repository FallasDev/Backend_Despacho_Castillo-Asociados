package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Formalitie;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_id")
    private ServiceEntity service;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "template_id", nullable = false)
    private int templateId;

    public FormalitieEntity() {
    }

    public FormalitieEntity(int id, int state, LocalDateTime createdAt, boolean active, ServiceEntity service, ClientEntity client, UserEntity user, int templateId) {
        this.id = id;
        this.state = state;
        this.createdAt = createdAt;
        this.active = active;
        this.service = service;
        this.client = client;
        this.user = user;
    }

    // Getters & setters ya los tienes con Lombok @Getter @Setter
}