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
    public void execute(int formalitieId, int newState, String additionalNote) throws MessagingException {

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


        if (newState == FormalitiesState.COMPLETED.getId()) {
            String completedBody = new HtmlContent().generateCompletedEmail(formalitie.getClient().getName(), formalitie.getService().getName(), additionalNote);
            this.emailService.sendHtmlEmail(
                    formalitie.getClient().getEmail(),
                    "Trámite completado",
                    completedBody
            );
        }

        if (newState == FormalitiesState.REFUSED.getId()) {
            String rejectedBody = new HtmlContent().generateRejectedEmail(formalitie.getClient().getName(), formalitie.getService().getName(), additionalNote);
            this.emailService.sendHtmlEmail(
                    formalitie.getClient().getEmail(),
                    "Trámite rechazado",
                    rejectedBody
            );
        }

        boolean response = formalitieRepository.changeFormalitieState(formalitieId, FormalitiesState.fromId(newState));

        if (!response) {
            throw new BadRequestException(messages.get("formality.exception.change_status.failed"));
        }

    }
}
