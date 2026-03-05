package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Role;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Role.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPARoleRepository extends JpaRepository<RoleEntity, Integer> {
    Optional<RoleEntity> findByName(String name);
    Optional<RoleEntity> findByNameAndActiveTrue(String name);
    Optional<RoleEntity> findByNameAndActiveFalse(String name);
    Page<RoleEntity> findAll(Pageable pageable);
}

