package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByIdClientUseCase {

    private final ClientRepository clientRepository;

    public FindByIdClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(int id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty() || !client.get().isActive()) {
            throw new EntityNotFoundException("Client with id " + id + " not found");
        }

        return client.get();
    }

}

