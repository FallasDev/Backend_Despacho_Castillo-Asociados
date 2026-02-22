package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class UpdateReportCategoryUseCase {

    private final ReportCategoryRepository reportCategoryRepository;
    private final Messages messages;

    public UpdateReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public ReportCategory execute(ReportCategoryRequest reportCategory, int id) {

        if (reportCategory == null) {
            throw new BadRequestException(messages.get("reportCategory.exception.update.cannot_be_null"));
        }

        if (reportCategory.getCategory() == null || reportCategory.getCategory().isEmpty()) {
            throw new BadRequestException(messages.get("reportCategory.exception.update.category.cannot_be_null"));
        }

        Optional<ReportCategory> existingReportCategory = reportCategoryRepository.findById(id);

        if (existingReportCategory.isEmpty()) {
            throw new BadRequestException(messages.get("reportCategory.exception.update.notfound", new Object[]{id}));
        }

        if (!existingReportCategory.get().isActive()) {
            throw new BadRequestException(messages.get("reportCategory.exception.update.is_not_active", new Object[]{id}));
        }

        ReportCategory updatedReportCategory = reportCategoryRepository.update(reportCategory, id);

        if (updatedReportCategory == null) {
            throw new BadRequestException(messages.get("reportCategory.exception.update.failed"));
        }

        return updatedReportCategory;
    }

}
