package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.Service;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.CustomField.CustomFieldEntity;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.Service.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAServiceRepository extends JpaRepository<ServiceEntity, Integer> {

    Optional<ServiceEntity> findByName(String name);

    boolean existsByNameAndActive(String name, boolean active);

    Optional<ServiceEntity> findByIdAndActiveIsTrue(int id);
    Optional<ServiceEntity> findByNameAndActiveIsTrue(String name);
    Optional<ServiceEntity> findByNameAndActiveIsFalse(String name);


    Page<ServiceEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);


}
