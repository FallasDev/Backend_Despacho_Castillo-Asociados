package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientResponse;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

import java.util.List;

public class FindAllClientsWithoutPaginationUseCase {

    private final ClientRepository clientRepository;

    public FindAllClientsWithoutPaginationUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientResponse> execute() {

        List<ClientResponse> clients = clientRepository.findAllWithoutPagination();

        if (clients.isEmpty()) {
            throw new EmptyListException("No clients found");
        }

        return clients;
    }

}
