package com.accountancy.despacho_castillo_asociados.domain.model.User;


import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;

public class User {

    private int id;
    private String name;
    private  String suername;
    private String photoProfileUrl;
    private String phoneNumber;
    private String perosnalId;
    private String email;
    private Role role;
    private String password;
    private String address;
    private boolean isActive;

    public User(){}

    public User(int id, String name, String suername, String photoProfileUrl, String phoneNumber, String perosnalId, String email, Role role, String password, String address, boolean isActive) {
        this.id = id;
        this.name = name;
        this.suername = suername;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.perosnalId = perosnalId;
        this.email = email;
        this.role = role;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

}