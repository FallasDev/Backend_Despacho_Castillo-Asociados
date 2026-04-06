package com.accountancy.despacho_castillo_asociados.application.usecase.Formalitie;

import com.accountancy.despacho_castillo_asociados.application.service.Email.EmailService;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.repository.Formalitie.FormalitieRepository;
import com.accountancy.despacho_castillo_asociados.shared.FormalitiesState;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.utils.HtmlContent;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;

public class ChangeFormalitieStateUseCase {

    private final FormalitieRepository formalitieRepository;
    private final Messages messages;
    private final EmailService emailService;

    public ChangeFormalitieStateUseCase(FormalitieRepository formalitieRepository, Messages messages, EmailService emailService) {
        this.formalitieRepository = formalitieRepository;
        this.messages = messages;
        this.emailService = emailService;
    }


    @Transactional
    public void execute(int formalitieId, int newState) throws MessagingException {

        int MIN_STATE = 1;
        int MAX_STATE = 4;

        if (formalitieId <= 0) {
            throw new BadRequestException(messages.get("formality.exception.change_status.formality.invalid", new Object[]{formalitieId}));
        }


        if (newState < MIN_STATE || newState > MAX_STATE) {
            throw new BadRequestException(messages.get("formality.exception.change_status.value.invalid", new Object[]{newState}));
        }

        Formalitie formalitie = formalitieRepository.findById(formalitieId).orElseThrow(
                () -> new BadRequestException(messages.get("formality.exception.change_status.formality.not_found", new Object[]{formalitieId}))
        );

        String body = new HtmlContent().generateInProgressEmail(formalitie.getClient().getName(), formalitie.getService().getName());



        if (newState == FormalitiesState.IN_PROGRESS.getId()) {
            this.emailService.sendHtmlEmail(
                formalitie.getClient().getEmail(),
                    "Cambio de estado de su trámite",
                    body
            );
        }

        boolean response = formalitieRepository.changeFormalitieState(formalitieId, FormalitiesState.fromId(newState));

        if (!response) {
            throw new BadRequestException(messages.get("formality.exception.change_status.failed"));
        }

    }
}
