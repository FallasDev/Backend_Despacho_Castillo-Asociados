package com.accountancy.despacho_castillo_asociados.application.service.Report;

import com.accountancy.despacho_castillo_asociados.application.usecase.Report.*;
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
    private UpdateReportUseCase updateReportUseCase;

    public ReportService(
            CreateReportUseCase createReportUseCase,
            DeactiveReportUseCase deactiveReportUseCase,
            FindReportsUseCase findReportUseCase,
            FindByIdReportUseCase findByIdReportUseCase,
            UpdateReportUseCase updateReportUseCase
    ) {
        this.createReportUseCase = createReportUseCase;
        this.deactiveReportUseCase = deactiveReportUseCase;
        this.findReportUseCase = findReportUseCase;
        this.findByIdReportUseCase = findByIdReportUseCase;
        this.updateReportUseCase = updateReportUseCase;
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

    public Report updateReport(ReportRequest reportRequest, int id) {
        return updateReportUseCase.execute(id,reportRequest);
    }


}
