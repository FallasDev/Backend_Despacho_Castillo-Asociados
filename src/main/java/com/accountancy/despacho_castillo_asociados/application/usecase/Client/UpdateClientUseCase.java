package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

public class UpdateClientUseCase {

    private final ClientRepository clientRepository;

    public UpdateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(ClientRequest clientRequest, int id) {

        if (clientRequest == null) {
            throw new BadRequestException("Client cannot be null");
        }

        if (clientRequest.getName() == null || clientRequest.getName().isEmpty()) {
            throw new BadRequestException("Client name cannot be null or empty");
        }

        Optional<Client> existingClient = clientRepository.findById(id);

        if (existingClient.isEmpty()) {
            throw new EntityNotFoundException("Client with id " + id + " does not exist");
        }

        if (!existingClient.get().isActive()) {
            throw new BadRequestException("Client with id " + id + " is not active");
        }

        Client updatedClient = clientRepository.update(clientRequest, id);

        if (updatedClient == null) {
            throw new BadRequestException("Failed to update Client with id " + id);
        }

        return updatedClient;
    }

}

