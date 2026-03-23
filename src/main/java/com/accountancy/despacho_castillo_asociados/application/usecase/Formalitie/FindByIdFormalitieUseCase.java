package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class FindByIdFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;
    private final Messages messages;

    public FindByIdFormalitieUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
    }

    @Transactional
    public Formalitie execute(int id) {

        Optional<Formalitie> formalitie = formalitieRepository.findById(id);

        if (formalitie.isEmpty()) {
            throw new EntityNotFoundException(messages.get("formality.exception.fetch.by_id.notfound", new Object[]{id}));
        }

        return formalitie.get();

    }

}
