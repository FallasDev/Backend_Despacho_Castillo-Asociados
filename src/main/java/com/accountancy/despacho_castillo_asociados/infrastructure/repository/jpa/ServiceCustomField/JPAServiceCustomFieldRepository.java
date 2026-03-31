package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.ServiceCustomField;

import aj.org.objectweb.asm.commons.Remapper;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.ServiceCustomField.ServiceCustomFieldEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAServiceCustomFieldRepository extends JpaRepository<ServiceCustomFieldEntity, Integer> {

    Page<ServiceCustomFieldEntity> findByServiceId(int serviceId, Pageable pageable);

    boolean existsByServiceId(int serviceId);

    @Modifying
    @Query("""
       UPDATE ServiceCustomFieldEntity scf
       SET scf.active = false
       WHERE scf.service.id = :serviceId
       """)
    int deactivateByServiceId(@Param("serviceId") int serviceId);



    List<ServiceCustomFieldEntity> findAllByServiceId(int serviceId);


    Optional<ServiceCustomFieldEntity> findByIdAndActive(int id, boolean active);

    Optional<ServiceCustomFieldEntity> findByNameAndActive(String name, boolean active);
}
