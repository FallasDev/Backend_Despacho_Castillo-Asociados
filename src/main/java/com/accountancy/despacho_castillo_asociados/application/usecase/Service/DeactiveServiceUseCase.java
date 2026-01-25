package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;

public class DeactiveServiceUseCase {

    private final ServiceRepository serviceRepository;

    private final Messages messages;

    public DeactiveServiceUseCase(ServiceRepository serviceRepository, Messages messages) {
        this.serviceRepository = serviceRepository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = serviceRepository.deactivate(id);

        if (!result) {
            throw new RuntimeException(messages.get("service.exception.deactive", new Object[]{id}));
        }
    }

}
