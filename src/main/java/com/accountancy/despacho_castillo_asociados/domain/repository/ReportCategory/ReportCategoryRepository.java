package com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory;



import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportCategoryRepository {

    ReportCategory create(ReportCategoryRequest reportCategoryRequest);

    ReportCategory update(ReportCategoryRequest reportCategoryRequest, int id);

    boolean deactivate(int id);

    void activate(int id);

    PageResult<ReportCategory> findByContainsCategoryLetterUseCase(String category, int page, int size);

    Optional<ReportCategory> findByTitleAndIsActive(String title);

    Optional<ReportCategory> findByTitleAndIsInactive(String title);

    Optional<ReportCategory> findByTitle(String title);

    Optional<ReportCategory> findByCategoryAndIsActive(String category);

    Optional<ReportCategory> findByCategoryAndIsInactive(String category);
    Optional<ReportCategory> findById(int id);

    Optional<ReportCategory> findByCategory(String category);

    PageResult<ReportCategory> findByContainsCategory(String category, int page, int size);

    PageResult<ReportCategory> findAll(int page, int size);

    boolean existsByCategoryAndIsActive(String category);

    boolean existsByCategoryAndIsInactive(String category);
}