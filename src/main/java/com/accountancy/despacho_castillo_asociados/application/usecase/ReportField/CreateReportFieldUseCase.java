package com.accountancy.despacho_castillo_asociados.application.usecase.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateReportFieldUseCase {

    private final ReportFieldRepository reportFieldRepository;
    private final TypeRepository typeRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    private final Messages messages;

    public CreateReportFieldUseCase(ReportFieldRepository reportFieldRepository, TypeRepository typeRepository, ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportFieldRepository = reportFieldRepository;
        this.typeRepository = typeRepository;
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public ReportField execute(ReportFieldRequest request) {

        if (request == null){
            throw new BadRequestException(messages.get("reportField.exception.create.cannot_be_null"));
        }

        if (request.getLabel() == null || request.getLabel().isEmpty()) {
            throw new BadRequestException(messages.get("reportField.exception.create.label.cannot_be_null"));
        }

        boolean existingReportField = reportFieldRepository.existsByLabelAndIsActive(request.getLabel());

        if (existingReportField) {
            throw new BadRequestException(messages.get("reportField.exception.create.already.exists"));
        }

        Optional<ReportField> inactiveReportField = reportFieldRepository.findByLabelAndIsInactive(request.getLabel());

        System.out.println("inactiveReportField: " + inactiveReportField.isPresent());

        if (inactiveReportField.isPresent()) {
            ReportField reactivatedReportField = inactiveReportField.get();
            reactivatedReportField.setActive(true);
            reportFieldRepository.activate(reactivatedReportField.getId());
            return reactivatedReportField;
        }


        Type type = typeRepository.findById(request.getTypeId()).orElse(null);

        if (type == null || !type.isActive()) {
            throw new BadRequestException(messages.get("reportField.exception.create.type.invalid", new Object[]{request.getTypeId()}));
        }

        ReportCategory reportCategory = reportCategoryRepository.findById(request.getReportCategoryId()).orElse(null);

        if (reportCategory == null || !reportCategory.isActive()) {
            throw new BadRequestException(messages.get("reportField.exception.create.reportCategory.invalid", new Object[]{request.getReportCategoryId()}));
        }

        ReportField createdReportField = reportFieldRepository.create(request, type, reportCategory);

        if (createdReportField == null) {
            throw new BadRequestException(messages.get("reportField.exception.create.failed"));
        }

        return createdReportField;
    }
}