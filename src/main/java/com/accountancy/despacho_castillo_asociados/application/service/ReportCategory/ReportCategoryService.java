package com.accountancy.despacho_castillo_asociados.application.service.ReportCategory;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory.*;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.DomainReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

public class ReportCategoryService {

    private CreateReportCategoryUseCase createReportCategoryUseCase;
    private UpdateReportCategoryUseCase updateReportCategoryUseCase;
    private DeactiveReportCategoryUseCase deactiveReportCategoryUseCase;
    private FindReportCategoryUseCase findReportCategoryUseCase;
    private FindByIdReportCategoryUseCase findByIdReportCategoryUseCase;

    public ReportCategoryService(CreateReportCategoryUseCase createReportCategoryUseCase, UpdateReportCategoryUseCase updateReportCategoryUseCase, DeactiveReportCategoryUseCase deactiveReportCategoryUseCase, FindReportCategoryUseCase findReportCategoryUseCase, FindByIdReportCategoryUseCase findByIdReportCategoryUseCase) {
        this.createReportCategoryUseCase = createReportCategoryUseCase;
        this.updateReportCategoryUseCase = updateReportCategoryUseCase;
        this.deactiveReportCategoryUseCase = deactiveReportCategoryUseCase;
        this.findReportCategoryUseCase = findReportCategoryUseCase;
        this.findByIdReportCategoryUseCase = findByIdReportCategoryUseCase;
    }

    public DomainReportCategory createReportCategory(ReportCategoryRequest reportCategoryRequest) {
        return createReportCategoryUseCase.execute(reportCategoryRequest);
    }

    public DomainReportCategory updateReportCategory(ReportCategoryRequest reportCategoryRequest, int id) {
        return updateReportCategoryUseCase.execute(reportCategoryRequest, id);
    }

    public void deactiveReportCategory(int id) {
        deactiveReportCategoryUseCase.execute(id);
    }

    public PageResult<DomainReportCategory> findByContainsCategoryLetterUseCase(String category, int page, int size) {
        return findReportCategoryUseCase.execute(category, page, size);
    }

    public DomainReportCategory findByIdReportCategory(int id) {
        return findByIdReportCategoryUseCase.execute(id);
    }

}
