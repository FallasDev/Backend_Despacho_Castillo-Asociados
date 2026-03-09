package com.accountancy.despacho_castillo_asociados.application.usecase.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindReportsFieldsUseCase {

    private final ReportFieldRepository reportFieldRepository;
    private Messages messages;

    public FindReportsFieldsUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        this.reportFieldRepository = reportFieldRepository;
        this.messages = messages;
    }

    public PageResult<ReportField> execute(int reportCategoryId,int page, int size) {

        if (reportCategoryId <= 0) {
            PageResult<ReportField> reportFieldPageResult = reportFieldRepository.findAll(page, size);

            if (reportFieldPageResult.content().isEmpty()) {
                throw new EmptyListException(messages.get("reportField.exception.fetch.all.none"));
            }

            return reportFieldPageResult;
        }

        PageResult<ReportField> reportFieldByReportCategory = reportFieldRepository.findByReportCategory(page,size,reportCategoryId);

        if (reportFieldByReportCategory.content().isEmpty()) {
            throw new EmptyListException(messages.get("reportField.exception.fetch.by_report_category.none", new Object[]{reportCategoryId}));
        }

        return reportFieldByReportCategory;
    }

}
