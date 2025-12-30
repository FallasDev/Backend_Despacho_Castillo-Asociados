package com.accountancy.despacho_castillo_asociados.domain.repository.CustomField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.List;
import java.util.Optional;

public interface CustomFieldRepository {

    CustomField create(CustomFieldRequest customField, Type type);
    CustomField update(CustomFieldRequest customField, int id, Type type);
    boolean deactivate(int id);
    void activate(int id);

    Optional<CustomField> findById(int id);
    Optional<CustomField> findByName(String name);
    Optional<CustomField> findByNameAndIsActive(String name);
    Optional<CustomField> findByNameAndIsInactive(String name);
    PageResult<CustomField> findAll(int page, int size);

    boolean existsByNameAndIsActive(String name);
    boolean existsByNameAndIsInactive(String name);

}
