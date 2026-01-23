package com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface FormalitieCustomFieldRepository {

    FormalitieCustomField create(FormalitieCustomFieldRequest request);
    FormalitieCustomField update(FormalitieCustomFieldRequest request, int id);
    boolean deactivate(int id);
    void activate(int id);

    Optional<FormalitieCustomField> findById(int id);
    PageResult<FormalitieCustomField> findAll(int page, int size);
    PageResult<FormalitieCustomField> findByFormalitieId(int formalitieId, int page, int size);

    Optional<FormalitieCustomField> findByFormalitieIdAndCustomFieldId(int formalitieId, int customFieldId);
    Optional<FormalitieCustomField> findByFormalitieIdAndCustomFieldIdAndIsInactive(int formalitieId, int customFieldId);




}
