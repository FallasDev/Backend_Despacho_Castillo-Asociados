package com.accountancy.despacho_castillo_asociados.infrastructure.entity.RefreshToken;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client.ClientEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.User.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity client;

    private Instant expiryDate;

    private boolean revoked;

    public RefreshTokenEntity() {
    }

    public RefreshTokenEntity(String token, UserEntity user, Instant expiryDate, boolean revoked) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }

}