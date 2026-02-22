package com.accountancy.despacho_castillo_asociados.application.service.ReportCategory;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory.*;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;

@Service
public class ReportCategoryService {

    private CreateReportCategoryUseCase createReportCategoryUseCase;
    private UpdateReportCategoryUseCase updateReportCategoryUseCase;
    private DeactiveReportCategoryUseCase deactiveReportCategoryUseCase;
    private FindReportsCategoryUseCase findReportsCategoryUseCase;
    private FindByIdReportCategoryUseCase findByIdReportCategoryUseCase;

    public ReportCategoryService(
            CreateReportCategoryUseCase createReportCategoryUseCase,
            UpdateReportCategoryUseCase updateReportCategoryUseCase,
            DeactiveReportCategoryUseCase deactiveReportCategoryUseCase,
            FindReportsCategoryUseCase findReportsCategoryUseCase,
            FindByIdReportCategoryUseCase findByIdReportCategoryUseCase
    ) {
        this.createReportCategoryUseCase = createReportCategoryUseCase;
        this.updateReportCategoryUseCase = updateReportCategoryUseCase;
        this.deactiveReportCategoryUseCase = deactiveReportCategoryUseCase;
        this.findReportsCategoryUseCase = findReportsCategoryUseCase;
        this.findByIdReportCategoryUseCase = findByIdReportCategoryUseCase;
    }

    public ReportCategory createReportCategory(ReportCategoryRequest reportCategoryRequest) {
        return createReportCategoryUseCase.execute(reportCategoryRequest);
    }

    public ReportCategory updateReportCategory(ReportCategoryRequest reportCategoryRequest, int id) {
        return updateReportCategoryUseCase.execute(reportCategoryRequest, id);
    }

    public void deactiveReportCategory(int id) {
        deactiveReportCategoryUseCase.execute(id);
    }

    public PageResult<ReportCategory> findByContainsCategoryLetterUseCase(String category, int page, int size) {
        return findReportsCategoryUseCase.execute(category, page, size);
    }

    public ReportCategory findByIdReportCategory(int id) {
        return findByIdReportCategoryUseCase.execute(id);
    }
}
