package com.accountancy.despacho_castillo_asociados.application.service.Formalitie;

import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.*;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

@Service
public class FormalitieService {

    private CreateFormalitieUseCase createFormalitieUseCase;
    private ChangeFormalitieStateUseCase changeFormalitieStateUseCase;
    private FindFormalitiesUseCase findFormalitiesUseCase;
    private FindByIdFormalitieUseCase findByIdFormalitieUseCase;
    private UpdateFormalitieUseCase updateFormalitieUseCase;
    private HandleFormalitieUseCase handleFormalitieUseCase;
    private GetFormalitiesCountByClientUseCase getFormalitiesCountByClientUseCase;

    public FormalitieService(CreateFormalitieUseCase createFormalitieUseCase,
                             ChangeFormalitieStateUseCase changeFormalitieStateUseCase,
                             FindFormalitiesUseCase findFormalitiesUseCase,
                             FindByIdFormalitieUseCase findByIdFormalitieUseCase,
                             HandleFormalitieUseCase handleFormalitieUseCase,
                             UpdateFormalitieUseCase updateFormalitieUseCase,
                             GetFormalitiesCountByClientUseCase getFormalitiesCountByClientUseCase) {
        this.createFormalitieUseCase = createFormalitieUseCase;
        this.changeFormalitieStateUseCase = changeFormalitieStateUseCase;
        this.findFormalitiesUseCase = findFormalitiesUseCase;
        this.findByIdFormalitieUseCase = findByIdFormalitieUseCase;
        this.handleFormalitieUseCase = handleFormalitieUseCase;
        this.updateFormalitieUseCase = updateFormalitieUseCase;
        this.getFormalitiesCountByClientUseCase = getFormalitiesCountByClientUseCase;
    }

    public Formalitie createFormalitie(FormalitieRequest formalitie) {
        return createFormalitieUseCase.execute(formalitie);
    }

    public void changeFormalitieState(int formalitieId, int newState, String additionalNote) throws MessagingException {
        changeFormalitieStateUseCase.execute(formalitieId, newState, additionalNote);
    }

    public PageResult<Formalitie> findFormalities(SearchFormalitie searchFormalitie, int page, int size) {
        return findFormalitiesUseCase.execute(searchFormalitie, page, size);
    }

    public Formalitie findFormalitieById(int id) {
        return findByIdFormalitieUseCase.execute(id);
    }


    public Formalitie updateFormalitie(int id, FormalitieRequestUpdate formalitieRequest) {
        return updateFormalitieUseCase.execute(formalitieRequest, id);
    }

    public FormalityClientStats countFormalitiesByClientId(int clientId) {
        return getFormalitiesCountByClientUseCase.execute(clientId);
    }

    public void handleFormalitie(int id, int userId) {
        handleFormalitieUseCase.execute(id, userId);
    }


}