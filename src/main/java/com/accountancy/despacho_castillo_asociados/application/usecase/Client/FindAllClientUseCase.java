package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientResponse;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindAllClientUseCase {

    private final ClientRepository clientRepository;

    public FindAllClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public PageResult<ClientResponse> execute(int page, int size) {

        PageResult<ClientResponse> clients = clientRepository.findAll(page, size);

        if (clients.content().isEmpty()) {
            throw new EmptyListException("No clients found");
        }

        return clients;
    }

}

