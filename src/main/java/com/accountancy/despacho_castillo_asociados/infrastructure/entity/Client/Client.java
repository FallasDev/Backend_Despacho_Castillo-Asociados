package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client;


import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private  String suername;

    @Column(nullable = true)
    private String photoProfileUrl;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String perosnalId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Role role;

    @Column (nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean isActive;

    public Client(int id, String name, String suername, String photoProfileUrl, String phoneNumber, String perosnalId, String email, Role role, String password, String address, boolean isActive) {

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

    public Client () {}

    public Client(String name, String suername, String photoProfileUrl, String phoneNumber, String perosnalId, String email, Role role, String password, String address, boolean isActive) {
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
}

