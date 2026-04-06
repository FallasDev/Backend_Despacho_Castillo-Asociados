package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import jakarta.transaction.Transactional;

public class FindFormalitiesUseCase {

    private final FormalitieRepository formalitieRepository;
    private final Messages messages;

    public FindFormalitiesUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
    }

    @Transactional
    public PageResult<Formalitie> execute(SearchFormalitie searchFormalitie, int page, int size) {

        PageResult<Formalitie> formalities = formalitieRepository.findByFilter(searchFormalitie, page, size);

        if (searchFormalitie.getStateId() == null) {
            searchFormalitie.setStateId(0);
        }

        if (formalities.content().isEmpty()) {
            throw new EmptyListException(messages.get("formality.exception.fetch.by_params"));
        }

        return formalities;
    }


}
