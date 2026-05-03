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

    // Get all active ServiceCustomFieldEntity with their custom fields, types and service using a single query to avoid N+1 problem
    @Query("select distinct scf from ServiceCustomFieldEntity scf " +
            "left join fetch scf.customFields cf " +
            "left join fetch cf.type t " +
            "left join fetch scf.service s " +
            "where scf.active = true")
    List<ServiceCustomFieldEntity> findAllWithCustomFieldsAndType();

    // Nuevo: paginación y filtro por nombre (solo activos)
    Page<ServiceCustomFieldEntity> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);

    Page<ServiceCustomFieldEntity> findByActiveTrue(Pageable pageable);

    // Nuevo: obtener entidades completas por ids con join fetch (para evitar N+1 después de paginar)
    @Query("select distinct scf from ServiceCustomFieldEntity scf " +
            "left join fetch scf.customFields cf " +
            "left join fetch cf.type t " +
            "left join fetch scf.service s " +
            "where scf.id in :ids")
    List<ServiceCustomFieldEntity> findAllByIdInWithCustomFieldsAndType(@Param("ids") List<Integer> ids);
}
