package com.accountancy.despacho_castillo_asociados.domain.model.Client;

public class ClientRequest {
    private String name;
    private  String surname;
    private String photoProfileUrl;
    private String phoneNumber;
    private String personalId;
    private String email;
    private String password;
    private String address;

    public ClientRequest(String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address) {
        this.name = name;
        this.surname = surname;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.personalId = personalId;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public ClientRequest(){}


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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
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
}
