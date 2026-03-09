package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Permission;

import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.repository.Permission.PermissionRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission.PermissionEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Permission.JPAPermissionRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PermissionRepositoryImpl implements PermissionRepository {

    private final JPAPermissionRepository jpaPermissionRepository;

    public PermissionRepositoryImpl(JPAPermissionRepository jpaPermissionRepository) {
        this.jpaPermissionRepository = jpaPermissionRepository;
    }

    @Override
    public Permission update(Permission permission, int id) {
        Optional<PermissionEntity> existing = jpaPermissionRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        PermissionEntity entity = existing.get();
        entity.setName(permission.getName());
        entity.setDescription(permission.getDescription());

        PermissionEntity updated = jpaPermissionRepository.save(entity);
        return getPermissionFromEntity(updated);
    }

    @Override
    public Optional<Permission> findById(int id) {
        return jpaPermissionRepository.findById(id)
                .map(this::getPermissionFromEntity);
    }

    @Override
    public Optional<Permission> fintByName(String name) {
        return jpaPermissionRepository.findByName(name)
                .map(this::getPermissionFromEntity);
    }

    @Override
    public Optional<Permission> fintByDescription(String description) {
        return jpaPermissionRepository.findByDescription(description)
                .map(this::getPermissionFromEntity);
    }

    @Override
    public PageResult<Permission> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<PermissionEntity> entityPage = jpaPermissionRepository.findAll(pageable);

        List<PermissionEntity> permissions = entityPage.getContent();

        return new PageResult<>(
                permissions.stream()
                        .map(this::getPermissionFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private Permission getPermissionFromEntity(@NonNull PermissionEntity entity) {
        return new Permission(
                entity.getId(),
                entity.getName(),
                entity.getDescription()
        );
    }
}

