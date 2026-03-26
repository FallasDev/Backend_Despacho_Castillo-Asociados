package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalityClientStats;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Stats;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class GetFormalitiesCountByClientUseCase {

    private final FormalitieRepository formalitieRepository;
    private final ClientRepository clientRepository;

    public GetFormalitiesCountByClientUseCase(FormalitieRepository formalitieRepository, ClientRepository clientRepository) {
        this.formalitieRepository = formalitieRepository;
        this.clientRepository = clientRepository;
    }

    public FormalityClientStats execute(int clientId) {
        Optional<Client> client = clientRepository.findById(clientId);

        if (client.isEmpty()) {
            throw new BadRequestException("Client with id " + clientId + " does not exist");
        }

        Stats stats = formalitieRepository.countByClientId(clientId);


        return new FormalityClientStats(stats.getTotal(), stats.getPending(), stats.getCompleted(), client.get());

    }
}
