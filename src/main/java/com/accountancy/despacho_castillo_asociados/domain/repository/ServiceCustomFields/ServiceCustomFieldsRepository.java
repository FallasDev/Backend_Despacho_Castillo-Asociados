package com.accountancy.despacho_castillo_asociados.domain.repository.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.List;
import java.util.Optional;

public interface ServiceCustomFieldsRepository {

    ServiceCustomField create(ServiceCustomFieldRequest request, DomainService service, List<CustomField> customFields);
    List<ServiceCustomField> createAll(List<ServiceCustomField> requests);
    ServiceCustomField update(ServiceCustomFieldRequest request,  DomainService service, List<CustomField> customFields,  int id);
    boolean deactivate(int id);
    boolean deactivateByServiceId(int serviceId);
    boolean deactivateByCustomFieldId(int customFieldId);
    void activate(int id);

    Optional<ServiceCustomField> findById(int id);
    PageResult<ServiceCustomField> findByServiceId(int serviceId, int page, int size);
    PageResult<ServiceCustomField> findAll(int page, int size);
    Optional<ServiceCustomField> findByFormalitieId(int customFieldId);

    List<ServiceCustomField> findAllWithoutPagination();

    boolean existsById(int id);
    boolean existsByServiceId(int serviceId);
    Optional<ServiceCustomField> findByNameAndIsInactive(String name);


}
