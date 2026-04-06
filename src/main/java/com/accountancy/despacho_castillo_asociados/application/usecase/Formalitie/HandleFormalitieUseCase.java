package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.User.UserRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.transaction.Transactional;

public class HandleFormalitieUseCase {

    private final FormalitieRepository formalitieRepository;
    private final UserRepository userRepository;
    private final Messages messages;

    public HandleFormalitieUseCase(FormalitieRepository formalitieRepository, UserRepository userRepository, Messages messages) {
        this.formalitieRepository = formalitieRepository;
        this.userRepository = userRepository;
        this.messages = messages;
    }

    @Transactional
    public void execute(int id, int userId) {

        if (id <= 0) {
            throw new BadRequestException(messages.get("formality.exception.handle.formality.invalid", new Object[]{id}));
        }

        if (userId <= 0) {
            throw new BadRequestException(messages.get("formality.exception.handle.user.invalid", new Object[]{userId}));
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user == null || !user.isActive()) {
            throw new BadRequestException(messages.get("formality.exception.handle.user.not_found", new Object[]{userId}));
        }

        Formalitie formalitie = formalitieRepository.findById(id).orElse(null);

        if (formalitie == null) {
            throw new BadRequestException(messages.get("formality.exception.handle.formality.not_found", new Object[]{id}));
        }

        if (formalitie.getState() != FormalitiesState.PENDING) {
            throw new BadRequestException(messages.get("formality.exception.handle.formality.not_pending", new Object[]{id}));
        }

        boolean response = formalitieRepository.handleFormalitie(id, user);

        if (!response) {
            throw new BadRequestException(messages.get("formality.exception.handle.formality.failed", new Object[]{id}));
        }

    }


}
