package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.DomainReportCategory;
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

    public DomainReportCategory execute(ReportCategoryRequest reportCategory) {

        if (reportCategory == null){
            throw new BadRequestException(messages.get("reportCategory.exception.create.cannot_be_null"));
        }

        if (reportCategory.getCategory() == null || reportCategory.getCategory().isEmpty()) {
            throw new BadRequestException(messages.get("reportCategory.exception.create.category.cannot_be_null"));
        }

        boolean existingReportCategory = reportCategoryRepository.existsByCategoryAndIsActive(reportCategory.getCategory());

        if (existingReportCategory) {
            throw new BadRequestException(messages.get("reportCategory.exception.create.already.exists"));
        }

        Optional<DomainReportCategory> inactiveReportCategory = reportCategoryRepository.findByCategoryAndIsInactive(reportCategory.getCategory());

        if (inactiveReportCategory.isPresent()) {
            DomainReportCategory reactivatedDomainReportCategory = inactiveReportCategory.get();
            reactivatedDomainReportCategory.setActive(true);
            reportCategoryRepository.activate(reactivatedDomainReportCategory.getId());
            return reactivatedDomainReportCategory;
        }

        return reportCategoryRepository.create(reportCategory);
    }
}
