package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Permission;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Permission.PermissionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAPermissionRepository extends JpaRepository<PermissionEntity, Integer> {
    Optional<PermissionEntity> findByName(String name);
    Optional<PermissionEntity> findByDescription(String description);
    Page<PermissionEntity> findAll(Pageable pageable);
}

