package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class ChangeFormalitieStateUseCase {

    private final FormalitieRepository formalitieRepository;

    public ChangeFormalitieStateUseCase(FormalitieRepository formalitieRepository) {
        this.formalitieRepository = formalitieRepository;
    }

    public void execute(int formalitieId, int newState) {

        int MIN_STATE = 1;
        int MAX_STATE = 4;

        if (formalitieId <= 0) {
            throw new BadRequestException("Formalitie ID must be greater than zero");
        }


        if (newState < MIN_STATE || newState > MAX_STATE) {
            throw new BadRequestException("Invalid state value");
        }

        boolean response = formalitieRepository.changeFormalitieState(formalitieId, FormalitiesState.fromId(newState));

        if (!response) {
            throw new BadRequestException("Failed to change the state of the formalitie");
        }

    }
}
