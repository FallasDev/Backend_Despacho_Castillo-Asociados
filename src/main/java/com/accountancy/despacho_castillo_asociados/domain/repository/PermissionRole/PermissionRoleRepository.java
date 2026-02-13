package com.accountancy.despacho_castillo_asociados.domain.repository.PermissionRole;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface PermissionRoleRepository {

    Client create(ClientRequest client);
    Client update(ClientRequest client, int id);
    void activate(int id);
    boolean deactivate(int id);

    Optional<Client> findById(int id);
    Optional<Client> fintByName(String name);
    Optional<Client> fintByNameAndIsActive(String name);
    Optional<Client> fintByNameAndIsInactive(String name);
    Optional<Client> fintBySurname(String surname);
    Optional<Client> fintBySurnameAndIsActive(String surname);
    Optional<Client> fintBySurnameAndIsInactive(String surname);
    PageResult<Client> findAll(int page, int size);
}
