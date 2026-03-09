package com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

public class DeactiveReportFieldValueUseCase {

    private final ReportFieldValueRepository repository;
    private final Messages messages;

    public DeactiveReportFieldValueUseCase(ReportFieldValueRepository repository, Messages messages) {
        this.repository = repository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean deactivated = repository.deactive(id);

        if (!deactivated) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.deactive", new Object[]{id}));
        }
    }
}
