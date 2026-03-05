package com.accountancy.despacho_castillo_asociados.application.service.Report;

import com.accountancy.despacho_castillo_asociados.application.usecase.Report.CreateReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.DeactiveReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.FindByIdReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.FindReportsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private CreateReportUseCase createReportUseCase;
    private DeactiveReportUseCase deactiveReportUseCase;
    private FindReportsUseCase findReportUseCase;
    private FindByIdReportUseCase findByIdReportUseCase;

    public ReportService(
            CreateReportUseCase createReportUseCase,
            DeactiveReportUseCase deactiveReportUseCase,
            FindReportsUseCase findReportUseCase,
            FindByIdReportUseCase findByIdReportUseCase
    ) {
        this.createReportUseCase = createReportUseCase;
        this.deactiveReportUseCase = deactiveReportUseCase;
        this.findReportUseCase = findReportUseCase;
        this.findByIdReportUseCase = findByIdReportUseCase;
    }

    public Report createReport(ReportRequest reportRequest) {
        return createReportUseCase.execute(reportRequest);
    }

    public void deactiveReport(int id) {
        deactiveReportUseCase.execute(id);
    }

    public PageResult<Report> findReport(String title, int page, int size) {
        return findReportUseCase.execute(title, page, size);
    }

    public Report findByIdReport(int id) {
        return findByIdReportUseCase.execute(id);
    }
}
