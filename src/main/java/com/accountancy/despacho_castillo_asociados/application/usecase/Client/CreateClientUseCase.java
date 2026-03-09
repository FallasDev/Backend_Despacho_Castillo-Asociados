package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;


public class CreateClientUseCase {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client execute(ClientRequest clientRequest) {

        if (clientRequest == null) {
            throw new BadRequestException("Client cannot be null");
        }

        if (clientRequest.getName() == null || clientRequest.getName().isEmpty()) {
            throw new BadRequestException("Client name cannot be null or empty");
        }

        boolean existingClient = clientRepository.fintByNameAndIsActive(clientRequest.getName()).isPresent();

        if (existingClient) {
            throw new BadRequestException("Client with name " + clientRequest.getName() + " already exists");
        }

        Optional<Client> inactiveClient = clientRepository.fintByNameAndIsInactive(clientRequest.getName());

        if (inactiveClient.isPresent()) {
            Client reactivatedClient = inactiveClient.get();
            clientRepository.activate(reactivatedClient.getId());
            return reactivatedClient;
        }

        return clientRepository.create(clientRequest);
    }

}

