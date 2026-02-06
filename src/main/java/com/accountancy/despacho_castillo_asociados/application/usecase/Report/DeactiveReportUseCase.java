package com.accountancy.despacho_castillo_asociados.application.usecase.Report;

import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;

public class DeactiveReportUseCase {

    private final ReportRepository reportRepository;

    private final Messages messages;

    public DeactiveReportUseCase(ReportRepository reportRepository, Messages messages) {
        this.reportRepository = reportRepository;
        this.messages = messages;
    }

    public void execute(int id) {
        boolean result = reportRepository.deactivate(id);

        if (!result) {
            throw new RuntimeException(messages.get("report.exception.deactive", new Object[]{id}));
        }
    }

}
