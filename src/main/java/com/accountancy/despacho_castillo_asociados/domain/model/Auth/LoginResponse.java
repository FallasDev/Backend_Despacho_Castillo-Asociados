package com.accountancy.despacho_castillo_asociados.domain.model.Auth;

public class LoginResponse {
    private String token;
    private long expiresIn;
    private String email;
    private String name;

    public LoginResponse() {}

    public LoginResponse(String token, long expiresIn, String email, String name) {
        this.token = token;
        this.expiresIn = expiresIn;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

