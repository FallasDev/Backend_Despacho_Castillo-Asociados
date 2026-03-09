package com.accountancy.despacho_castillo_asociados.application.usecase.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveReportFieldUseCase {

    private final ReportFieldRepository reportFieldRepository;
    private Messages messages;

    public DeactiveReportFieldUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        this.reportFieldRepository = reportFieldRepository;
        this.messages = messages;
    }

    public void execute(int id) {

        boolean exists = reportFieldRepository.existsById(id);

        if (!exists) {
            throw new BadRequestException(messages.get("reportField.exception.deactive", new Object[]{id}));
        }

        reportFieldRepository.deactive(id);

    }

}
