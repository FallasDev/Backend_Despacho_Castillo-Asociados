package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import lombok.NonNull;

public class FindFormalitiesUseCase {

    private final FormalitieRepository formalitieRepository;
    private final Messages messages;

    public FindFormalitiesUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
    }

    public PageResult<Formalitie> execute(SearchFormalitie searchFormalitie, int page, int size) {

        PageResult<Formalitie> formalities = formalitieRepository.findByFilter(searchFormalitie, page, size);

        if (formalities.content().isEmpty()) {
            throw new EmptyListException(messages.get("formality.exception.fetch.by_params"));
        }

        return formalities;
    }

    @NonNull
    public boolean hasFilter(@NonNull SearchFormalitie searchFormalitie) {
        return searchFormalitie.getClientName() != null &&
               searchFormalitie.getServiceName() != null &&
               searchFormalitie.getStateId() <= 0 &&
                searchFormalitie.getStartDate() != null &&
                searchFormalitie.getEndDate() != null;
    }


}
