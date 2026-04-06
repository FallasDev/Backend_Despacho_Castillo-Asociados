package com.accountancy.despacho_castillo_asociados.application.usecase.Service;

import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainServiceMostPopularServices;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;

import java.util.List;

public class FindMostPopularServicesUseCase {

    private final FormalitieRepository formalitieRepository;

    public FindMostPopularServicesUseCase(FormalitieRepository formalitieRepository) {
        this.formalitieRepository = formalitieRepository;
    }

        public List<DomainServiceMostPopularServices> execute() {
            return formalitieRepository.findMostPopularServices();
        }

}
