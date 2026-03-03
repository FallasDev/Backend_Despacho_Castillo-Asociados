package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields.FormalitieCustomFieldsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JPAFormalitieCustomFieldsRepository extends JpaRepository<FormalitieCustomFieldsEntity, Integer> {

    Page<FormalitieCustomFieldsEntity> findByFormalitieId(int formalitieId, Pageable pageable);

    Page<FormalitieCustomFieldsEntity> findByActive(boolean active, Pageable pageable);

    List<FormalitieCustomFieldsEntity> findByCustomFieldIdAndActive(int customFieldId, boolean active);
    
    Optional<FormalitieCustomFieldsEntity>
    findByFormalitieIdAndCustomFieldIdAndActive(
            int formalitieId,
            int customFieldId,
            boolean active);

    @Modifying
    @Query(
            """
            UPDATE FormalitieCustomFieldsEntity fcf
            SET fcf.active = false
            WHERE fcf.customFieldId = :customFieldId
            """
    )
    int deactivateByCustomField(int customFieldId);
}
