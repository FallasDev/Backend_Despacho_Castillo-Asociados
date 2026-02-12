package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class ChangeFormalitieStateUseCase {

    private final FormalitieRepository formalitieRepository;
    private final Messages messages;

    public ChangeFormalitieStateUseCase(FormalitieRepository formalitieRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
    }

    public void execute(int formalitieId, int newState) {

        int MIN_STATE = 1;
        int MAX_STATE = 4;

        if (formalitieId <= 0) {
            throw new BadRequestException(messages.get("formality.exception.change_status.formality.invalid", new Object[]{formalitieId}));
        }


        if (newState < MIN_STATE || newState > MAX_STATE) {
            throw new BadRequestException(messages.get("formality.exception.change_status.value.invalid", new Object[]{newState}));
        }

        boolean response = formalitieRepository.changeFormalitieState(formalitieId, FormalitiesState.fromId(newState));

        if (!response) {
            throw new BadRequestException(messages.get("formality.exception.change_status.failed"));
        }

    }
}
