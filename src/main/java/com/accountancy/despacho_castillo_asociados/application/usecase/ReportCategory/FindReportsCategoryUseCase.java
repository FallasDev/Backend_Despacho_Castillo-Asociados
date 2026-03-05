package com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindReportsCategoryUseCase {

    private final ReportCategoryRepository reportCategoryRepository;
    private final Messages messages;

    public FindReportsCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        this.reportCategoryRepository = reportCategoryRepository;
        this.messages = messages;
    }

    public PageResult<ReportCategory> execute(String category, int page, int size) {

        if (category != null && !category.isEmpty()) {

            PageResult<ReportCategory> reportCategoryByCategory = reportCategoryRepository.findByContainsCategoryLetterUseCase(category, page, size);

            if (reportCategoryByCategory.content().isEmpty()) {
                throw new EmptyListException(messages.get("reportCategory.exception.fetch.by_name_like.none"));
            }

            return reportCategoryByCategory;
        }

        PageResult<ReportCategory> domainReportCategory = reportCategoryRepository.findAll(page, size);

        if (domainReportCategory.content().isEmpty()) {
            throw new EmptyListException(messages.get("reportCategory.exception.fetch.all.none"));
        }

        return domainReportCategory;
    }

}
