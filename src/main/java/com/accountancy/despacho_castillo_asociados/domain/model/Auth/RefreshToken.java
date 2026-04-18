package com.accountancy.despacho_castillo_asociados.domain.model.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.User.User;

import java.time.Instant;

public class RefreshToken {

    private Long id;

    private String token;

    private User user;

    private Instant expiryDate;

    private boolean revoked;

    public RefreshToken() {
    }

    public RefreshToken(Long id, String token, User user, Instant expiryDate, boolean revoked) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
        this.revoked = revoked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
}
