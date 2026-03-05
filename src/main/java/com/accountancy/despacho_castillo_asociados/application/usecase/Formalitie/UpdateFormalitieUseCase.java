package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateFormalitieUseCase {


    private final FormalitieRepository formalitieRepository;

    private final Messages messages;

    public UpdateFormalitieUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
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

        Formalitie updatedFormalitie = formalitieRepository.update(formalitie, id);

         if (updatedFormalitie == null) {
             throw new BadRequestException(messages.get("formality.exception.update.failed"));
         }

        return updatedFormalitie;

    }
}
