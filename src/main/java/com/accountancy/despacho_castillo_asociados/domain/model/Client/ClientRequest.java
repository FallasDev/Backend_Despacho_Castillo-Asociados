package com.accountancy.despacho_castillo_asociados.domain.model.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;

public class ClientRequest {
    private String name;
    private  String suername;
    private String photoProfileUrl;
    private String phoneNumber;
    private String perosnalId;
    private String email;
    private String password;
    private String address;

    public ClientRequest(String name, String suername, String photoProfileUrl, String phoneNumber, String perosnalId, String email, String password, String address) {
        this.name = name;
        this.suername = suername;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.perosnalId = perosnalId;
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

    public String getSuername() {
        return suername;
    }

    public void setSuername(String suername) {
        this.suername = suername;
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
        return perosnalId;
    }

    public void setPerosnalId(String perosnalId) {
        this.perosnalId = perosnalId;
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
