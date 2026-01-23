package com.accountancy.despacho_castillo_asociados.infrastructure.repository.jpa.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.infrastructure.entity.FormalitieCustomFields.FormalitieCustomFieldsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPAFormalitieCustomFieldsRepository extends JpaRepository<FormalitieCustomFieldsEntity, Integer> {

    Page<FormalitieCustomFieldsEntity> findByFormalitieId(int formalitieId, Pageable pageable);


    Optional<FormalitieCustomField> findByFormalitieIdAndCustomFieldIdAndActive(int formalitieId, int customFieldId, boolean active);
}
