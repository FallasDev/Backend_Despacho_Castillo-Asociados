package com.accountancy.despacho_castillo_asociados.domain.model.Auth;

public class LoginRequest {
    private String email;
    private String password;
    private boolean isClient;

    public LoginRequest() {}

    public LoginRequest(String email, String password, boolean isClient) {
        this.email = email;
        this.password = password;
        this.isClient = isClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean isClient) {
        this.isClient = isClient;
    }
}

