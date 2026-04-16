package com.accountancy.despacho_castillo_asociados.infrastructure.entity.User;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(name = "suername", nullable = false)
    private String surname;

    @Column(nullable = true)
    private String photoProfileUrl;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(name = "perosnal_id", nullable = false, unique = true)
    private String personalId;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean isActive;

    public UserEntity() {}

    public UserEntity(String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, RoleEntity role, String password, String address, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.personalId = personalId;
        this.email = email;
        this.role = role;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
    }

    public UserEntity(int id, String name, String surname, String photoProfileUrl, String phoneNumber, String personalId, String email, RoleEntity role, String password, String address, boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.photoProfileUrl = photoProfileUrl;
        this.phoneNumber = phoneNumber;
        this.personalId = personalId;
        this.email = email;
        this.role = role;
        this.password = password;
        this.address = address;
        this.isActive = isActive;
    }
}
