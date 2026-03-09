package com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateReportFieldValueUseCase {

    private final ReportFieldValueRepository repository;
    private final ReportFieldRepository reportFieldRepository;
    private final ReportRepository reportRepository;
    private final Messages messages;

    public CreateReportFieldValueUseCase(ReportFieldValueRepository repository, ReportFieldRepository reportFieldRepository, ReportRepository reportRepository, Messages messages) {
        this.repository = repository;
        this.reportFieldRepository = reportFieldRepository;
        this.reportRepository = reportRepository;
        this.messages = messages;
    }

    public ReportFieldValue execute(ReportFieldValueRequest request) {

        if (request == null) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.create.cannot_be_null"));
        }

        Optional<ReportFieldValue> existingRelation = repository.findByReportAndField(request.getReportId(), request.getReportFieldId());

        Optional<Report> report = reportRepository.findById(request.getReportId());
        Optional<ReportField> field = reportFieldRepository.findById(request.getReportFieldId());

        if (existingRelation.isPresent() && existingRelation.get().isActive()) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.create.name.relation.already.exists"));
        }

        if (report.isEmpty() || !report.get().isActive()) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.create.report.not_found",
                    new Object[]{request.getReportId()}));
        }

        if (field.isEmpty() || !field.get().isActive()) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.create.reportfield.not_found",
                    new Object[]{request.getReportFieldId()}));
        }

        Optional<ReportFieldValue> inactive = existingRelation.filter(
                relation -> !relation.isActive()
        );

        if (inactive.isPresent()) {
            ReportFieldValue toActivate = inactive.get();
            repository.activate(toActivate.getId());
            return toActivate;
        }

        ReportFieldValue created = repository.create(request);

        if (created == null) {
            throw new BadRequestException(messages.get("reportfieldvalue.exception.create.failed"));
        }

        return created;




    }



}
