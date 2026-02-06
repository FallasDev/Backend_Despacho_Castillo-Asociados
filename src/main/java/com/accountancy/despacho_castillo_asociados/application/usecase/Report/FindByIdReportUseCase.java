package com.accountancy.despacho_castillo_asociados.application.usecase.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Service.ServiceRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import jakarta.persistence.EntityNotFoundException;

public class FindByIdReportUseCase {

    private final ReportRepository reportRepository;
    private final Messages messages;

    public FindByIdReportUseCase(ReportRepository reportRepository, Messages messages) {
        this.reportRepository = reportRepository;
        this.messages = messages;
    }

    public DomainReport execute(int id) {
        DomainReport domainReport = reportRepository.findById(id).orElse(null);

        if (domainReport == null || !domainReport.isActive()) {
            throw new EntityNotFoundException(messages.get("report.exception.fetch.by_id.notfound", new Object[]{id}));
        }

        return domainReport;
    }

}
