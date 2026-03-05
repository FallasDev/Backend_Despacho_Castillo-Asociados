package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;

public class FindByIdReportCategoryUseCase {

    private final ReportCategoryRepository reportCategoryRepository;
    private final Messages messages;

    public FindByIdReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public ReportCategory execute(int id) {
        ReportCategory reportCategory = reportCategoryRepository.findById(id).orElse(null);

        if (reportCategory == null || !reportCategory.isActive()) {
            throw new EntityNotFoundException(messages.get("reportCategory.exception.fetch.by_id.notfound", new Object[]{id}));
        }

        return reportCategory;
    }

}
