package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = true)
    private String photoProfileUrl;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String personalId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean isActive;

    public ClientEntity() {}

    public ClientEntity(String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.personalId = personalId;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
    }

    public ClientEntity(int id, String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address, boolean isActive) {
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
    }
}

