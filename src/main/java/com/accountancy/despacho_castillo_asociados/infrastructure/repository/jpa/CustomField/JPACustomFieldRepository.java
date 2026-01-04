package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.CustomField;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Type.TypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPACustomFieldRepository extends JpaRepository<CustomFieldEntity, Integer> {

    Optional<CustomFieldEntity> findByName(String name);

    boolean existsByNameAndActive(String name, boolean active);

    Optional<CustomFieldEntity> findByIdAndActiveIsTrue(int id);
    Optional<CustomFieldEntity> findByNameAndActiveIsTrue(String name);
    Optional<CustomFieldEntity> findByNameAndActiveIsFalse(String name);
    Page<CustomFieldEntity> findByType(TypeEntity type, Pageable pageable);
    Page<CustomFieldEntity> findByRequired(boolean required, Pageable pageable);
}
