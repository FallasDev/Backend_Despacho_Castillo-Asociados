package com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.PermissionRole.PermissionRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "permissions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PermissionRole> permissions = new ArrayList<>();

    @Column(nullable = false)
    private boolean active;

    public Role() {}

    public Role(String name, String description, List<PermissionRole> permissions, boolean active) {
        this.name = name;
        this.description = description;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
        this.active = active;
    }

    public Role(Integer id, String name, String description, List<PermissionRole> permissions, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissions = permissions != null ? permissions : new ArrayList<>();
        this.active = active;
    }

}