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

public class UpdateReportFieldUseCase {

    private final ReportFieldRepository reportFieldRepository;
    private final Messages messages;
    private final TypeRepository typeRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    public UpdateReportFieldUseCase(ReportFieldRepository reportFieldRepository,
                                    TypeRepository typeRepository,
                                    Messages messages, ReportCategoryRepository reportCategoryRepository) {
        this.reportFieldRepository = reportFieldRepository;
        this.typeRepository = typeRepository;
        this.messages = messages;
        this.reportCategoryRepository = reportCategoryRepository;
    }

    public ReportField execute(ReportFieldRequest request, int id) {

        if (request == null) {
            throw new BadRequestException(messages.get("reportField.exception.update.cannot_be_null"));
        }

        if (request.getLabel() == null || request.getLabel().isEmpty()) {
            throw new BadRequestException(messages.get("reportField.exception.update.label.cannot_be_null"));
        }

        Optional<ReportField> existingReportField = reportFieldRepository.findById(id);

        if (existingReportField.isEmpty() || !existingReportField.get().isActive()) {
            throw new BadRequestException(messages.get("reportField.exception.update.notfound", new Object[]{id}));
        }

        Type type = typeRepository.findById(request.getTypeId()).orElse(null);

        if (type == null || !type.isActive()) {
            throw new BadRequestException(messages.get("reportField.exception.update.type.invalid", new Object[]{request.getTypeId()}));
        }

        ReportCategory category = reportCategoryRepository.findById(request.getReportCategoryId()).orElse(null);

        if (category == null || !category.isActive()) {
            throw new BadRequestException(messages.get("reportField.exception.update.report_category.invalid", new Object[]{request.getReportCategoryId()}));
        }

        ReportField updatedReportField = reportFieldRepository.update(request, id, type, category);

        if (updatedReportField == null) {
            throw new BadRequestException(messages.get("reportField.exception.update.failed"));
        }

        return updatedReportField;


    }
}
