package com.accountancy.despacho_castillo_asociados.application.service.Client;

import com.accountancy.despacho_castillo_asociados.application.usecase.Client.*;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientResponse;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.UpdateClientRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeactivateClientUseCase deactivateClientUseCase;
    private final FindAllClientUseCase findAllClientUseCase;
    private final FindByIdClientUseCase findByIdClientUseCase;
    private final FindByNameClientUseCase findByNameClientUseCase;
    private final FindBySurnameClientUseCase findBySurnameClientUseCase;
    private final FindAllClientsWithoutPaginationUseCase findAllClientsWithoutPaginationUseCase;

    public ClientService(
            CreateClientUseCase createClientUseCase,
            UpdateClientUseCase updateClientUseCase,
            DeactivateClientUseCase deactivateClientUseCase,
            FindAllClientUseCase findAllClientUseCase,
            FindByIdClientUseCase findByIdClientUseCase,
            FindByNameClientUseCase findByNameClientUseCase,
            FindBySurnameClientUseCase findBySurnameClientUseCase, FindAllClientsWithoutPaginationUseCase findAllClientsWithoutPaginationUseCase
    ) {
        this.createClientUseCase = createClientUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deactivateClientUseCase = deactivateClientUseCase;
        this.findAllClientUseCase = findAllClientUseCase;
        this.findByIdClientUseCase = findByIdClientUseCase;
        this.findByNameClientUseCase = findByNameClientUseCase;
        this.findBySurnameClientUseCase = findBySurnameClientUseCase;
        this.findAllClientsWithoutPaginationUseCase = findAllClientsWithoutPaginationUseCase;
    }

    public Client createClient(ClientRequest clientRequest) throws MessagingException {
        return createClientUseCase.execute(clientRequest);
    }

    public Client updateClient(UpdateClientRequest clientRequest, int id) {
        return updateClientUseCase.execute(clientRequest, id);
    }

    public void deactivateClient(int id) {
        deactivateClientUseCase.execute(id);
    }

    public PageResult<ClientResponse> findAllClients(int page, int size) {
        return findAllClientUseCase.execute(page, size);
    }

    public Client findByIdClient(int id) {
        return findByIdClientUseCase.execute(id);
    }

    public Client findByNameClient(String name) {
        return findByNameClientUseCase.execute(name);
    }

    public Client findBySurnameClient(String surname) {
        return findBySurnameClientUseCase.execute(surname);
    }

    public List<ClientResponse> findAllClientsWithoutPagination() {
        return findAllClientsWithoutPaginationUseCase.execute();
    }
}

