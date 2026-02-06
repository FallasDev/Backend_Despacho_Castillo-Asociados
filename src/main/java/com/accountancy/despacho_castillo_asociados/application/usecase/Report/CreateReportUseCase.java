package com.accountancy.despacho_castillo_asociados.application.usecase.Report;

import com.accountancy.despacho_castillo_asociados.application.service.Report.ReportService;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;

import java.util.Optional;

public class CreateReportUseCase {

    private final ReportRepository reportRepository;

    private final Messages messages;

    public CreateReportUseCase(ReportRepository reportRepository, Messages messages) {
        this.reportRepository = reportRepository;
        this.messages = messages;
    }

    public DomainReport execute(ReportRequest report) {

        if (report == null){
            throw new BadRequestException(messages.get("report.exception.create.cannot_be_null"));
        }

        if (report.getTitle() == null || report.getTitle().isEmpty()) {
            throw new BadRequestException(messages.get("report.exception.create.title.cannot_be_null"));
        }

        boolean existingReport = reportRepository.existsByTitleAndIsActive(report.getTitle());

        if (existingReport) {
            throw new BadRequestException(messages.get("report.exception.create.already.exists"));
        }

        Optional<DomainReport> inactiveReport = reportRepository.findByTitleAndIsInactive(report.getTitle());

        if (inactiveReport.isPresent()) {
            DomainReport reactivatedDomainReport = inactiveReport.get();
            reactivatedDomainReport.setActive(true);
            reportRepository.activate(reactivatedDomainReport.getId());
            return reactivatedDomainReport;
        }

        return reportRepository.create(report);
    }
}
