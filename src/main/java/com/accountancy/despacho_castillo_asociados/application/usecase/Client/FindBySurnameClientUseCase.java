package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class FindBySurnameClientUseCase {

    private final ClientRepository clientRepository;

    public FindBySurnameClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(String surname) {

        if (surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Client surname cannot be null or empty");
        }

        Optional<Client> client = clientRepository.fintBySurnameAndIsActive(surname);

        if (client.isEmpty()) {
            throw new EntityNotFoundException("Client with surname " + surname + " not found");
        }

        return client.get();
    }

}

