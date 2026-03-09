package com.accountancy.despacho_castillo_asociados.application.usecase.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jakarta.persistence.EntityNotFoundException;

public class FindByIdReportFieldUseCase {

     private final ReportFieldRepository reportFieldRepository;
     private Messages messages;

    public FindByIdReportFieldUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        this.reportFieldRepository = reportFieldRepository;
        this.messages = messages;
    }

    public ReportField execute(int id) {

        ReportField reportField = reportFieldRepository.findById(id).orElse(null);

        if (reportField == null || !reportField.isActive()) {
            throw new EntityNotFoundException(messages.get("reportField.exception.fetch.by_id.notfound"));
        }

        return reportField;
     }

}
