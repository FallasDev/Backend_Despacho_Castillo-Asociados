package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.Client.ClientRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateFormalitieUseCase {


    private final FormalitieRepository formalitieRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final Messages messages;

    public UpdateFormalitieUseCase(FormalitieRepository formalitieRepository, ClientRepository clientRepository, UserRepository userRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.messages = messages;
    }


    public Formalitie execute(FormalitieRequest formalitie, int id) {

        if (formalitie == null) {
            throw new BadRequestException(messages.get("formality.exception.update.cannot_be_null"));
        }

        if (formalitie.getServiceId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.update.service.invalid"));
        }

        if (formalitie.getClientId() <= 0) {
            throw new BadRequestException(messages.get("formality.exception.update.client.invalid"));
        }


        Optional<Formalitie> existingCustomField = formalitieRepository.findById(id);

        if (existingCustomField.isEmpty()) {
            throw new BadRequestException(messages.get("formality.exception.update.notfound", new Object[]{id}));
        }

        if (!existingCustomField.get().getState().equals(FormalitiesState.PENDING)) {
            throw new BadRequestException(messages.get("formality.exception.update.is_not_updatable", new Object[]{id}));
        }

        Client client = clientRepository.findById(formalitie.getClientId()).orElse(null);

        if (client == null) {
            throw new BadRequestException(messages.get("formality.exception.update.client.not_found", new Object[]{formalitie.getClientId()}));
        }

        User user = userRepository.findById(formalitie.getUserId()).orElse(null);

        Formalitie updatedFormalitie = formalitieRepository.update(formalitie, id, client, user);

         if (updatedFormalitie == null) {
             throw new BadRequestException(messages.get("formality.exception.update.failed"));
         }

        return updatedFormalitie;

    }
}
