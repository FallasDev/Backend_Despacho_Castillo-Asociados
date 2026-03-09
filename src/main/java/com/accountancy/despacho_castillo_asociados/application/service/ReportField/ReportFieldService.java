package com.accountancy.despacho_castillo_asociados.application.service.ReportField;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportField.*;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class ReportFieldService {

    private CreateReportFieldUseCase createReportFieldUseCase;
    private UpdateReportFieldUseCase updateReportFieldUseCase;
    private DeactiveReportFieldUseCase deactiveReportFieldUseCase;
    private FindByIdReportFieldUseCase findByIdReportFieldUseCase;
    private FindReportsFieldsUseCase findReportsFieldsUseCase;

    public ReportFieldService(
            CreateReportFieldUseCase createReportFieldUseCase,
            UpdateReportFieldUseCase updateReportFieldUseCase,
            DeactiveReportFieldUseCase deactiveReportFieldUseCase,
            FindByIdReportFieldUseCase findByIdReportFieldUseCase,
            FindReportsFieldsUseCase findReportsFieldsUseCase
    ) {
        this.createReportFieldUseCase = createReportFieldUseCase;
        this.updateReportFieldUseCase = updateReportFieldUseCase;
        this.deactiveReportFieldUseCase = deactiveReportFieldUseCase;
        this.findByIdReportFieldUseCase = findByIdReportFieldUseCase;
        this.findReportsFieldsUseCase = findReportsFieldsUseCase;
    }

    public ReportField createReportField(ReportFieldRequest reportFieldRequest) {
        return createReportFieldUseCase.execute(reportFieldRequest);
    }

    public ReportField updateReportField(ReportFieldRequest reportFieldRequest, int id) {
        return updateReportFieldUseCase.execute(reportFieldRequest, id);
    }

    public void deactiveReportField(int id) {
        deactiveReportFieldUseCase.execute(id);
    }

    public ReportField findByIdReportField(int id) {
        return findByIdReportFieldUseCase.execute(id);
    }

    public PageResult<ReportField> findReportsFields(int page, int size, int reportCategoryId) {
        return findReportsFieldsUseCase.execute(reportCategoryId,page, size);
    }



}
