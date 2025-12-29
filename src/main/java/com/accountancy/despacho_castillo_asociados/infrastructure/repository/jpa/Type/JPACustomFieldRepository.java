package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Type;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomFieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPACustomFieldRepository extends JpaRepository<CustomFieldEntity, Integer> {

    Optional<CustomFieldEntity> findByName(String name);

    boolean existsByNameAndActive(String name, boolean active);

    Optional<CustomFieldEntity> findByIdAndActiveIsTrue(int id);
    Optional<CustomFieldEntity> findByNameAndActiveIsTrue(String name);
    Optional<CustomFieldEntity> findByNameAndActiveIsFalse(String name);

}
