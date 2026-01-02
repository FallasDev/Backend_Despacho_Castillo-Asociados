package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPAServiceCustomFieldRepository extends JpaRepository<ServiceCustomFieldEntity, Integer> {

    Page<ServiceCustomFieldEntity> findByServiceId(int serviceId, Pageable pageable);
    Optional<ServiceCustomFieldEntity> findByServiceIdAndCustomFieldId(int serviceId, int customFieldId);

    boolean existsByServiceId(int serviceId);
    Optional<ServiceCustomFieldEntity> findByServiceIdAndCustomFieldIdAndActive(int serviceId, int customFieldId, boolean isActive);



}
