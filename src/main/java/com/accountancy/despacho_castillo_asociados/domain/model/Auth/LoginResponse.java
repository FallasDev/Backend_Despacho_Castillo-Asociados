package com.accountancy.despacho_castillo_asociados.domain.model.Auth;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private long userId;
    private String role;
    private String name;

    public LoginResponse() {}

    public LoginResponse(String token, long expiresIn, long userId, String role, String name) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.userId = userId;
        this.role = role;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

