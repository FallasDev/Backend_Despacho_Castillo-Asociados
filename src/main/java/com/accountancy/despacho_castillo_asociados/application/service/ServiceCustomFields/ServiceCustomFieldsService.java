package com.accountancy.despacho_castillo_asociados.application.service.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.CreateServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.DeactiveServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindByIdServiceCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.FindServicesCustomFieldsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class ServiceCustomFieldsService {

    CreateServiceCustomFieldsUseCase createServiceCustomFieldsUseCase;
    DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldsUseCase;
    FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldsUseCase;
    FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase;

    public ServiceCustomFieldsService(CreateServiceCustomFieldsUseCase createServiceCustomFieldsUseCase,
                                      DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldsUseCase,
                                      FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldsUseCase,
                                      FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase) {
        this.createServiceCustomFieldsUseCase = createServiceCustomFieldsUseCase;
        this.deactiveServiceCustomFieldsUseCase = deactiveServiceCustomFieldsUseCase;
        this.findByIdServiceCustomFieldsUseCase = findByIdServiceCustomFieldsUseCase;
        this.findServicesCustomFieldsUseCase = findServicesCustomFieldsUseCase;
    }

    public ServiceCustomField createServiceCustomField(ServiceCustomFieldRequest request) {
        return createServiceCustomFieldsUseCase.execute(request);
    }

    public void deactiveServiceCustomField(int id) {
        deactiveServiceCustomFieldsUseCase.execute(id);
    }

    public ServiceCustomField findByIdServiceCustomField(int id) {
        // Not implemented
        return null;
    }

    public PageResult<ServiceCustomField> findServicesCustomFields(int serviceId, int page, int size) {
        return findServicesCustomFieldsUseCase.execute(serviceId, page, size);
    }



}
