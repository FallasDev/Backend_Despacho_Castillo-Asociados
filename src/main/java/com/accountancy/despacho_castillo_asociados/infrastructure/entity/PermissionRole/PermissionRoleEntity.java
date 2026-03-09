package com.accountancy.despacho_castillo_asociados.infrastructure.entity.PermissionRole;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission.PermissionEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "role")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "permissions_roles")
public class PermissionRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    private PermissionEntity permission;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    public PermissionRoleEntity() {}

    public PermissionRoleEntity(PermissionEntity permission, RoleEntity role) {
        this.permission = permission;
        this.role = role;
    }

    public PermissionRoleEntity(Integer id, PermissionEntity permission, RoleEntity role) {
        this.id = id;
        this.permission = permission;
        this.role = role;
    }
}

