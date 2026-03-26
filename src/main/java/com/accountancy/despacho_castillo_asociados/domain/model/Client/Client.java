package com.accountancy.despacho_castillo_asociados.domain.model.Client;

import java.time.LocalDate;

public class Client {
    private int id;
    private String name;
    private  String surname;
    private String photoProfileUrl;
    private String phoneNumber;
    private String personalId;
    private String email;
    private String password;
    private String address;
    private boolean enabled;
    private boolean isActive;
    private LocalDate createdAt;

    public Client(){}

    public Client(int id, String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address, boolean isActive, boolean enabled, LocalDate createdAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.personalId = personalId;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhotoProfileUrl() {
        return photoProfileUrl;
    }

    public void setPhotoProfileUrl(String photoProfileUrl) {
        this.photoProfileUrl = photoProfileUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPerosnalId() {
        return personalId;
    }

    public void setPerosnalId(String perosnalId) {
        this.personalId = perosnalId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
