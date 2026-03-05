package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;

public class DeactiveReportCategoryUseCase {

    private final ReportCategoryRepository reportCategoryRepository;

    private final Messages messages;

    public DeactiveReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = reportCategoryRepository.deactivate(id);

        if (!result) {
            throw new RuntimeException(messages.get("reportCategory.exception.deactive", new Object[]{id}));
        }
    }

}
