package com.accountancy.despacho_castillo_asociados.domain.repository.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.List;
import java.util.Optional;

public interface FormalitieCustomFieldRepository {

    FormalitieCustomField create(FormalitieCustomFieldRequest request);
    List<FormalitieCustomField> createBatch(List<FormalitieCustomField> formalitieCustomFields);
    List<FormalitieCustomField> updateBatch(List<FormalitieCustomField> formalitieCustomFields);
    FormalitieCustomField update(FormalitieCustomFieldRequest request, int id);
    boolean deactivate(int id);
    boolean deactivateByCustomFieldId(int customFieldId);
    void activate(int id);

    Optional<FormalitieCustomField> findById(int id);
    PageResult<FormalitieCustomField> findAll(int page, int size);

    Optional<FormalitieCustomField> findByCustomFieldIdAndIsInactive(int customFieldId);

    void updateFilePath(int formalitieCustomFieldId, String publicId);



}
