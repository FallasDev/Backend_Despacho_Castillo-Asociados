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

    @Column(name = "client_id", nullable = false)
    private int clientId;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "service", nullable = false)
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "client", insertable = false, updatable = false)
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "user", insertable = false, updatable = false)
    private UserEntity user;
    // Client and user pending implementation


}
