package com.accountancy.despacho_castillo_asociados.application.service.Service;


import com.accountancy.despacho_castillo_asociados.application.usecase.Service.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainServiceService {

    private CreateServiceUseCase createServiceUseCase;
    private UpdateServiceUseCase updateServiceUseCase;
    private DeactiveServiceUseCase deactiveServiceUseCase;
    private FindServicesUseCase findServicesUseCase;
    private FindByIdServiceUseCase findByIdServiceUseCase;

    public DomainServiceService(
            CreateServiceUseCase createServiceUseCase,
            UpdateServiceUseCase updateServiceUseCase,
            DeactiveServiceUseCase deactiveServiceUseCase,
            FindServicesUseCase findServicesUseCase,
            FindByIdServiceUseCase findByIdServiceUseCase
    ) {
        this.createServiceUseCase = createServiceUseCase;
        this.updateServiceUseCase = updateServiceUseCase;
        this.deactiveServiceUseCase = deactiveServiceUseCase;
        this.findServicesUseCase = findServicesUseCase;
        this.findByIdServiceUseCase = findByIdServiceUseCase;
    }

    public DomainService createService(ServiceRequest serviceRequest) {
        return createServiceUseCase.execute(serviceRequest);
    }

    public DomainService updateService(ServiceRequest serviceRequest, int id) {
        return updateServiceUseCase.execute(serviceRequest, id);
    }

    public void deactiveService(int id) {
        deactiveServiceUseCase.execute(id);
    }

    public PageResult<DomainService> findServices(String name, int page, int size) {
        return findServicesUseCase.execute(name, page, size);
    }

    public DomainService findByIdService(int id) {
        return findByIdServiceUseCase.execute(id);
    }


}
