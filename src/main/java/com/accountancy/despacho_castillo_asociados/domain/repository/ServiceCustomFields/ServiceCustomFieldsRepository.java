package com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ServiceCustomFieldsRepository {

    ServiceCustomField create(ServiceCustomFieldRequest request);
    ServiceCustomField update(ServiceCustomFieldRequest request, int id);
    boolean deactivate(int id);
    boolean deactivateByServiceId(int serviceId);
    boolean deactivateByCustomFieldId(int customFieldId);
    void activate(int id);

    Optional<ServiceCustomField> findById(int id);
    PageResult<ServiceCustomField> findByServiceId(int serviceId, int page, int size);
    PageResult<ServiceCustomField> findAll(int page, int size);


    boolean existsById(int id);
    boolean existsByServiceId(int serviceId);
    Optional<ServiceCustomField> findByServiceIdAndCustomFieldIdAndIsInactive(int serviceId, int customFieldId);
    Optional<ServiceCustomField> findByServiceIdAndCustomFieldId(int serviceId, int customFieldId);


}
