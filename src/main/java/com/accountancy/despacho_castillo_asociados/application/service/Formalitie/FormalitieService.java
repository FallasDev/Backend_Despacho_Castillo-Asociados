package com.accountancy.despacho_castillo_asociados.application.service.Formalitie;

import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.ChangeFormalitieStateUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.CreateFormalitieUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.FindByIdFormalitieUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie.FindFormalitiesUseCase;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class FormalitieService {

    private CreateFormalitieUseCase createFormalitieUseCase;
    private ChangeFormalitieStateUseCase changeFormalitieStateUseCase;
    private FindFormalitiesUseCase findFormalitiesUseCase;
    private FindByIdFormalitieUseCase findByIdFormalitieUseCase;

    public FormalitieService(CreateFormalitieUseCase createFormalitieUseCase,
                             ChangeFormalitieStateUseCase changeFormalitieStateUseCase,
                             FindFormalitiesUseCase findFormalitiesUseCase,
                             FindByIdFormalitieUseCase findByIdFormalitieUseCase) {
        this.createFormalitieUseCase = createFormalitieUseCase;
        this.changeFormalitieStateUseCase = changeFormalitieStateUseCase;
        this.findFormalitiesUseCase = findFormalitiesUseCase;
        this.findByIdFormalitieUseCase = findByIdFormalitieUseCase;
    }

    public Formalitie createFormalitie(FormalitieRequest formalitie) {
        return createFormalitieUseCase.execute(formalitie);
    }

    public void changeFormalitieState(int formalitieId, int newState) {
        changeFormalitieStateUseCase.execute(formalitieId, newState);
    }

    public PageResult<Formalitie> findFormalities(SearchFormalitie searchFormalitie, int page, int size) {
        return findFormalitiesUseCase.execute(searchFormalitie, page, size);
    }

    public Formalitie findFormalitieById(int id) {
        return findByIdFormalitieUseCase.execute(id);
    }


}
