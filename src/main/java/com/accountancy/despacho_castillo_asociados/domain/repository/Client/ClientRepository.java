package com.accountancy.despacho_castillo_asociados.domain.repository.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ClientRepository {

    Client create(ClientRequest clientRequest);
    Client update(ClientRequest clientRequest, int id);
    void activate(int id);
    boolean deactivate(int id);

    Optional<Client> findById(int id);
    Optional<Client> findByName(String name);
    Optional<Client> findByNameAndIsActive(String name);
    Optional<Client> findByNameAndIsInactive(String name);
    Optional<Client> findBySurname(String surname);
    Optional<Client> findBySurnameAndIsActive(String surname);
    Optional<Client> findBySurnameAndIsInactive(String surname);
    Optional<Client> findByEmailAndActive(String email);
    PageResult<Client> findAll(int page, int size);

    void enabledClient(int id);


    boolean existByEmailAndPasswordAndActive(String email, String password);
}
