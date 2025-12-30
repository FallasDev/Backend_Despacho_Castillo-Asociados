package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPATypeRepository extends JpaRepository<TypeEntity, Integer> {

    TypeEntity findByName(String name);

    boolean existsByNameAndActive(String name, boolean active);

    Optional<TypeEntity> findByIdAndActiveIsTrue(int id);

    Optional<TypeEntity> findByNameAndActiveIsTrue(String name);
    Optional<TypeEntity> findByNameAndActiveIsFalse(String name);
}
