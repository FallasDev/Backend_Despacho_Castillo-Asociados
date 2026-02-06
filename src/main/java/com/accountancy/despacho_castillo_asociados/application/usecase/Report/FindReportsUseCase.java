package com.accountancy.despacho_castillo_asociados.application.usecase.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindReportsUseCase {

    private final ReportRepository reportRepository;
    private final Messages messages;

    public FindReportsUseCase(ReportRepository reportRepository, Messages messages) {
        this.reportRepository = reportRepository;
        this.messages = messages;
    }

    public PageResult<DomainReport> execute(String title, int page, int size) {

        if (title != null && !title.isEmpty()) {

            PageResult<DomainReport> reportByCategory = reportRepository.findByContainsTitleLetterUseCase(title, page, size);

            if (reportByCategory.content().isEmpty()) {
                throw new EmptyListException(messages.get("report.exception.fetch.by_name_like.none"));
            }

            return reportByCategory;
        }

        PageResult<DomainReport> domainReport = reportRepository.findAll(page, size);

        if (domainReport.content().isEmpty()) {
            throw new EmptyListException(messages.get("report.exception.fetch.all.none"));
        }

        return domainReport;
    }

}
