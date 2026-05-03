package com.accountancy.despacho_castillo_asociados.application.service.ServiceCustomFields;

import com.accountancy.despacho_castillo_asociados.application.usecase.ServiceCustomFields.*;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.infrastructure.dto.servicecustomfield.ServiceCustomFieldDTO;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCustomFieldsService {

    CreateServiceCustomFieldsUseCase createServiceCustomFieldsUseCase;
    DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldsUseCase;
    FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldsUseCase;
    UpdateServiceCustomFieldsUseCase updateServiceCustomFieldsUseCase;
    FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase;
    FindAllServiceCustomFields findAllServiceCustomFields;
    FindAllServiceCustomFieldsDto findAllServiceCustomFieldsDto;
    FindAllServiceCustomFieldsDtoPaginated findAllServiceCustomFieldsDtoPaginated;

    public ServiceCustomFieldsService(CreateServiceCustomFieldsUseCase createServiceCustomFieldsUseCase,
                                      DeactiveServiceCustomFieldsUseCase deactiveServiceCustomFieldsUseCase,
                                      FindByIdServiceCustomFieldsUseCase findByIdServiceCustomFieldsUseCase,
                                      FindServicesCustomFieldsUseCase findServicesCustomFieldsUseCase,
                                      UpdateServiceCustomFieldsUseCase updateServiceCustomFieldsUseCase,
                                      FindAllServiceCustomFields findAllServiceCustomFields,
                                      FindAllServiceCustomFieldsDto findAllServiceCustomFieldsDto,
                                      FindAllServiceCustomFieldsDtoPaginated findAllServiceCustomFieldsDtoPaginated) {
        this.createServiceCustomFieldsUseCase = createServiceCustomFieldsUseCase;
        this.deactiveServiceCustomFieldsUseCase = deactiveServiceCustomFieldsUseCase;
        this.findByIdServiceCustomFieldsUseCase = findByIdServiceCustomFieldsUseCase;
        this.findServicesCustomFieldsUseCase = findServicesCustomFieldsUseCase;
        this.updateServiceCustomFieldsUseCase = updateServiceCustomFieldsUseCase;
        this.findAllServiceCustomFields = findAllServiceCustomFields;
        this.findAllServiceCustomFieldsDto = findAllServiceCustomFieldsDto;
        this.findAllServiceCustomFieldsDtoPaginated = findAllServiceCustomFieldsDtoPaginated;
    }

    public ServiceCustomField createServiceCustomField(ServiceCustomFieldRequest request) {
        return createServiceCustomFieldsUseCase.execute(request);
    }

    public void deactiveServiceCustomField(int id) {
        deactiveServiceCustomFieldsUseCase.execute(id);
    }

    public ServiceCustomField findByIdServiceCustomField(int id) {
        return findByIdServiceCustomFieldsUseCase.execute(id);
    }

    public PageResult<ServiceCustomField> findServicesCustomFields(int serviceId, int page, int size) {
        return findServicesCustomFieldsUseCase.execute(serviceId, page, size);
    }

    public ServiceCustomField updateServiceCustomField(int id, ServiceCustomFieldRequest request) {
        return updateServiceCustomFieldsUseCase.execute(request, id);
    }

    public List<ServiceCustomField> findAllServiceCustomFields() {
        return findAllServiceCustomFields.execute();
    }

    public List<ServiceCustomFieldDTO> findAllServiceCustomFieldsDto() {
        return findAllServiceCustomFieldsDto.execute();
    }

    public PageResult<ServiceCustomFieldDTO> findAllServiceCustomFieldsDtoPaginated(String name, int page, int size) {
        return findAllServiceCustomFieldsDtoPaginated.execute(name, page, size);
    }


}
