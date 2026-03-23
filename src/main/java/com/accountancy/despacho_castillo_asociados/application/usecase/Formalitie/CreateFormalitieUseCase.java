package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class CreateFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;
    private final ServiceRepository serviceRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final Messages messages;

    public CreateFormalitieUseCase(FormalitieRepository formalitieRepository, ServiceRepository serviceRepository, ClientRepository clientRepository, UserRepository userRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.serviceRepository = serviceRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.messages = messages;
    }

    @Transactional
    public Formalitie execute(FormalitieRequest formalitie) {


        if (formalitie == null) {
            throw new BadRequestException(messages.get("formality.exception.create.cannot_be_null"));
        }

        if (formalitie.getServiceId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.create.service.invalid", new Object[]{formalitie.getServiceId()}));
        }

        if (formalitie.getClientId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.create.client.invalid", new Object[]{formalitie.getClientId()}));
        }

        Optional<DomainService> service = serviceRepository.findById(formalitie.getServiceId());

        if (service.isEmpty()) {
            throw new BadRequestException(messages.get("formality.exception.create.service.not_found", new Object[]{formalitie.getServiceId()}));
        }

        if (!service.get().isActive()) {
            throw new BadRequestException(messages.get("formality.exception.create.service.not_found", new Object[]{formalitie.getServiceId()}));
        }

        // Additional validation for client existence can be added here if a ClientRepository is available
        Client client = clientRepository.findById(formalitie.getClientId()).orElse(null);

        if (client == null || !client.isActive()) {
            throw new BadRequestException(messages.get("formality.exception.create.client.not_found", new Object[]{formalitie.getClientId()}));
        }

        User user = userRepository.findById(formalitie.getUserId()).orElse(null);

        Formalitie createdFormalitie = formalitieRepository.create(formalitie, service.get(), client, user);

        if (createdFormalitie == null) {
            throw new BadRequestException(messages.get("formality.exception.create.failed"));
        }

        return createdFormalitie;

    }

}
