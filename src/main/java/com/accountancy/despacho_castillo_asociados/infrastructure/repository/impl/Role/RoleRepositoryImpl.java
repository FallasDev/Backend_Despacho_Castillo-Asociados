package com.accountancy.despacho_castillo_asociados.infrastructure.repository.impl.Role;

import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Role.RoleRepository;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Role.JPARoleRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private final JPARoleRepository jpaRoleRepository;

    public RoleRepositoryImpl(JPARoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role create(RoleRequest roleRequest) {
        RoleEntity entity = new RoleEntity(
                roleRequest.getName(),
                roleRequest.getDescription(),
                new java.util.ArrayList<>(),
                true
        );

        RoleEntity saved = jpaRoleRepository.save(entity);
        return getRoleFromEntity(saved);
    }

    @Override
    public Role Update(RoleRequest roleRequest, int id) {
        Optional<RoleEntity> existing = jpaRoleRepository.findById(id);

        if (existing.isEmpty()) {
            return null;
        }

        RoleEntity entity = existing.get();
        entity.setName(roleRequest.getName());
        entity.setDescription(roleRequest.getDescription());

        RoleEntity updated = jpaRoleRepository.save(entity);
        return getRoleFromEntity(updated);
    }

    @Override
    public boolean deactivate(int id) {
        Optional<RoleEntity> existing = jpaRoleRepository.findById(id);
        if (existing.isPresent()) {
            RoleEntity entity = existing.get();
            entity.setActive(false);
            jpaRoleRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public void activate(int id) {
        Optional<RoleEntity> existing = jpaRoleRepository.findById(id);
        if (existing.isPresent()) {
            RoleEntity entity = existing.get();
            entity.setActive(true);
            jpaRoleRepository.save(entity);
        }
    }

    @Override
    public Optional<Role> findById(int id) {
        return jpaRoleRepository.findById(id)
                .map(this::getRoleFromEntity);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(this::getRoleFromEntity);
    }

    @Override
    public Optional<Role> findByNameAndIsActive(String name) {
        return jpaRoleRepository.findByNameAndActiveTrue(name)
                .map(this::getRoleFromEntity);
    }

    @Override
    public Optional<Role> findByNameAndIsInactive(String name) {
        return jpaRoleRepository.findByNameAndActiveFalse(name)
                .map(this::getRoleFromEntity);
    }

    @Override
    public PageResult<Role> findAll(int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        Page<RoleEntity> entityPage = jpaRoleRepository.findAll(pageable);

        List<RoleEntity> roles = entityPage.getContent();

        return new PageResult<>(
                roles.stream()
                        .map(this::getRoleFromEntity)
                        .toList(),
                page,
                size,
                entityPage.getTotalElements(),
                entityPage.getTotalPages()
        );
    }

    @NonNull
    private Role getRoleFromEntity(@NonNull RoleEntity entity) {
        return new Role(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                new java.util.ArrayList<>(),
                entity.isActive()
        );
    }
}

