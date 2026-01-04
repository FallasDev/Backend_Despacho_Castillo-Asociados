package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;
    private final ServiceRepository serviceRepository;

    public CreateFormalitieUseCase(FormalitieRepository formalitieRepository, ServiceRepository serviceRepository) {
        this.formalitieRepository = formalitieRepository;
        this.serviceRepository = serviceRepository;
    }

    public Formalitie execute(FormalitieRequest formalitie) {


        if (formalitie == null) {
            throw new BadRequestException("Formalitie cannot be null");
        }

        if (formalitie.getServiceId() <= 0) {
            throw new BadRequestException("Service ID must be greater than zero");
        }

        if (formalitie.getClientId() <= 0) {
            throw new BadRequestException("Client ID must be greater than zero");
        }

        Optional<DomainService> service = serviceRepository.findById(formalitie.getServiceId());

        if (service.isEmpty()) {
            throw new BadRequestException("Service with ID " + formalitie.getServiceId() + " does not exist");
        }

        return formalitieRepository.create(formalitie, service.get());

    }

}
