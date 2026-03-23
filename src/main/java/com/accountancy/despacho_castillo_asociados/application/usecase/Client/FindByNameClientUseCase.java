package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindByNameClientUseCase {

    private final ClientRepository clientRepository;

    public FindByNameClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Client name cannot be null or empty");
        }

        Optional<Client> client = clientRepository.findByNameAndIsActive(name);

        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client with name " + name + " not found");
        }

        return client.get();
    }

}

