package com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface FormalitieRepository {

    Formalitie create(FormalitieRequest formalitieRequest, DomainService domainService, Client client, User user);
    Formalitie update(FormalitieRequest formalitieRequest, int id, DomainService service, Client client, User user);
    boolean changeFormalitieState(int id, FormalitiesState state);


    Optional<Formalitie> findById(int id);
    PageResult<Formalitie> findAll(int page, int size);
    PageResult<Formalitie> findByFilter(SearchFormalitie searchFormalitie, int page, int size);

    PageResult<Formalitie> findByClientId(int clientId, int page, int size);
    PageResult<Formalitie> findByUserId(int userId, int page, int size);
     PageResult<Formalitie> findByServiceId(int serviceId, int page, int size);

     boolean handleFormalitie(int id, User user);

     Stats countByClientId(int clientId);

}
