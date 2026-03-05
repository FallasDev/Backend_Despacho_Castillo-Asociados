package com.accountancy.despacho_castillo_asociados.application.usecase.Client;

import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class DeactivateClientUseCase {

    private final ClientRepository clientRepository;

    public DeactivateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void execute(int id) {
        boolean result = clientRepository.deactivate(id);

        if (!result) {
            throw new BadRequestException("Failed to deactivate Client with id " + id);
        }
    }

}

