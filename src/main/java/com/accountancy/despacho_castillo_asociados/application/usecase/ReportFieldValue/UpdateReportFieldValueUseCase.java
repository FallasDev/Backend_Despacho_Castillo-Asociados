package com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateReportFieldValueUseCase {

    private final ReportFieldValueRepository repository;
    private final Messages messages;

    public UpdateReportFieldValueUseCase(ReportFieldValueRepository repository, Messages messages) {
        this.repository = repository;
        this.messages = messages;
    }

     public ReportFieldValue execute(ReportFieldValueRequest request, int id) {
         if (request == null) {
             throw new BadRequestException(messages.get("reportfieldvalue.exception.update.cannot_be_null"));
         }

         Optional<ReportFieldValue> existingRelation = repository.findByReportAndField(
                    request.getReportId(),
                    request.getReportFieldId()
         );

         if (existingRelation.isEmpty() || !existingRelation.get().isActive()) {
             throw new BadRequestException(messages.get("reportfieldvalue.exception.update.notfound",
                        new Object[]{request.getReportId(), request.getReportFieldId()}));
         }

         ReportFieldValue updated = repository.update(request, id);

         if (updated == null) {
             throw new BadRequestException(messages.get("reportfieldvalue.exception.update.failed"));
         }

         return updated;
     }

}
