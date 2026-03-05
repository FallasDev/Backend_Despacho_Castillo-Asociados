package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.PermissionRole;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.PermissionRole.PermissionRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAPermissionRoleRepository extends JpaRepository<PermissionRoleEntity, Integer> {
    Optional<PermissionRoleEntity> findById(Integer id);
    Page<PermissionRoleEntity> findByRoleId(Integer roleId, Pageable pageable);
    Page<PermissionRoleEntity> findByPermissionId(Integer permissionId, Pageable pageable);
    Page<PermissionRoleEntity> findAll(Pageable pageable);
}

