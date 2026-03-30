package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    public ClientEntity() {}

    public ClientEntity(String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address, boolean isActive, boolean enabled) {
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
    }

    public ClientEntity(int id, String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, String password, String address, boolean isActive, boolean enabled) {
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
    }
}

