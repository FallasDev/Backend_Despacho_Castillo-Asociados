package com.accountancy.despacho_castillo_asociados.domain.model.Auth;

import java.time.LocalDateTime;

public class VerificationCode {

    private int id;

    private String email;           // o userId si prefieres

    private String code;            // "483920" o "A7B9K2"

    private LocalDateTime expiryDate;

    private boolean used;

    private LocalDateTime createdAt;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public VerificationCode() {}

    public VerificationCode(int id, String email, String code, LocalDateTime expiryDate, boolean used, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.expiryDate = expiryDate;
        this.used = used;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
