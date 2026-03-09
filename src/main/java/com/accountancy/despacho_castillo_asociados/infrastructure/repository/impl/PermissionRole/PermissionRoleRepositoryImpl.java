package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole.PermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.PermissionRole.PermissionRoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.PermissionRole.JPAPermissionRoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRoleRepositoryImpl implements PermissionRoleRepository {

    private final JPAPermissionRoleRepository jpaPermissionRoleRepository;

    public PermissionRoleRepositoryImpl(JPAPermissionRoleRepository jpaPermissionRoleRepository) {
        this.jpaPermissionRoleRepository = jpaPermissionRoleRepository;
    }

    @Override
    public PermissionRole create(PermissionRoleRequest permissionRoleRequest) {
        PermissionRoleEntity entity = new PermissionRoleEntity();
        entity.setPermission(new com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission.PermissionEntity(
                permissionRoleRequest.getPermission().getId(),
                permissionRoleRequest.getPermission().getName(),
                permissionRoleRequest.getPermission().getDescription()
        ));
        entity.setRole(new com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity(
                permissionRoleRequest.getIdRole(),
                "",
                "",
                new java.util.ArrayList<>(),
                true
        ));

        PermissionRoleEntity saved = jpaPermissionRoleRepository.save(entity);
        return getPermissionRoleFromEntity(saved);
    }

    @Override
    public PermissionRole update(PermissionRoleRequest permissionRoleRequest, int id) {
        Optional<PermissionRoleEntity> existing = jpaPermissionRoleRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        PermissionRoleEntity entity = existing.get();
        entity.setPermission(new com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission.PermissionEntity(
                permissionRoleRequest.getPermission().getId(),
                permissionRoleRequest.getPermission().getName(),
                permissionRoleRequest.getPermission().getDescription()
        ));

        PermissionRoleEntity updated = jpaPermissionRoleRepository.save(entity);
        return getPermissionRoleFromEntity(updated);
    }

    @Override
    public boolean delete(int id) {
        if (jpaPermissionRoleRepository.existsById(id)) {
            jpaPermissionRoleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<PermissionRole> findById(int id) {
        return jpaPermissionRoleRepository.findById(id)
                .map(this::getPermissionRoleFromEntity);
    }

    @Override
    public PageResult<PermissionRole> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PermissionRoleEntity> entityPage = jpaPermissionRoleRepository.findAll(pageable);

        List<PermissionRoleEntity> permissionRoles = entityPage.getContent();

        return new PageResult<>(
                permissionRoles.stream()
                        .map(this::getPermissionRoleFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @Override
    public PageResult<PermissionRole> findByIdRole(int idRole, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PermissionRoleEntity> entityPage = jpaPermissionRoleRepository.findByRoleId(idRole, pageable);

        List<PermissionRoleEntity> permissionRoles = entityPage.getContent();

        return new PageResult<>(
                permissionRoles.stream()
                        .map(this::getPermissionRoleFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @Override
    public PageResult<PermissionRole> findByPermissionId(int permissionId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PermissionRoleEntity> entityPage = jpaPermissionRoleRepository.findByPermissionId(permissionId, pageable);

        List<PermissionRoleEntity> permissionRoles = entityPage.getContent();

        return new PageResult<>(
                permissionRoles.stream()
                        .map(this::getPermissionRoleFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private PermissionRole getPermissionRoleFromEntity(@NonNull PermissionRoleEntity entity) {
        Permission domainPermission = new Permission(
                entity.getPermission().getId(),
                entity.getPermission().getName(),
                entity.getPermission().getDescription()
        );

        Role domainRole = new Role(
                entity.getRole().getId(),
                entity.getRole().getName(),
                entity.getRole().getDescription(),
                new java.util.ArrayList<>(),
                entity.getRole().isActive()
        );

        return new PermissionRole(
                entity.getId(),
                entity.getRole().getId(),
                domainPermission,
                domainRole
        );
    }
}

