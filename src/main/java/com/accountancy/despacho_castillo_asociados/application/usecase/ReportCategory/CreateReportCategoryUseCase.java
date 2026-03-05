package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateReportCategoryUseCase {

    private final ReportCategoryRepository reportCategoryRepository;

    private final Messages messages;

    public CreateReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public ReportCategory execute(ReportCategoryRequest reportCategory) {

        if (reportCategory == null){
            throw new BadRequestException(messages.get("reportCategory.exception.create.cannot_be_null"));
        }

        if (reportCategory.getName() == null || reportCategory.getName().isEmpty()) {
            throw new BadRequestException(messages.get("reportCategory.exception.create.name.cannot_be_null"));
        }

        boolean existingReportCategory = reportCategoryRepository.existsByCategoryAndIsActive(reportCategory.getName());

        if (existingReportCategory) {
            throw new BadRequestException(messages.get("reportCategory.exception.create.already.exists"));
        }

        Optional<ReportCategory> inactiveReportCategory = reportCategoryRepository.findByCategoryAndIsInactive(reportCategory.getName());

        if (inactiveReportCategory.isPresent()) {
            ReportCategory reactivatedReportCategory = inactiveReportCategory.get();
            reactivatedReportCategory.setActive(true);
            reportCategoryRepository.activate(reactivatedReportCategory.getId());
            return reactivatedReportCategory;
        }

        ReportCategory createdReportCategory = reportCategoryRepository.create(reportCategory);

        if (createdReportCategory == null) {
            throw new BadRequestException(messages.get("reportCategory.exception.create.failed"));
        }

        return createdReportCategory;
    }
}
