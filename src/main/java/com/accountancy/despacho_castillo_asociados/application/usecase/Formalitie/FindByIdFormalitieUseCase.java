package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;

    public FindByIdFormalitieUseCase(FormalitieRepository formalitieRepository) {
        this.formalitieRepository = formalitieRepository;
    }

    public Formalitie execute(int id) {

        Optional<Formalitie> formalitie = formalitieRepository.findById(id);

        if (formalitie.isEmpty()) {
            throw new EntityNotFoundException("Formalitie with ID " + id + " not found");
        }

        return formalitie.get();

    }

}
