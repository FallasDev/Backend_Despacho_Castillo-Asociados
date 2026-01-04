package com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface FormalitieRepository {

    Formalitie create(FormalitieRequest formalitieRequest, DomainService domainService);
    boolean changeFormalitieState(int id, FormalitiesState state);

    Optional<Formalitie> findById(int id);
    PageResult<Formalitie> findAll(int page, int size);
    PageResult<Formalitie> findByFilter(SearchFormalitie searchFormalitie, int page, int size);


}
