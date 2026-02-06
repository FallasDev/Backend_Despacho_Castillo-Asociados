package com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory;



import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.DomainReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportCategoryRepository {

    DomainReportCategory create(ReportCategoryRequest reportCategoryRequest);

    DomainReportCategory update(ReportCategoryRequest reportCategoryRequest, int id);

    boolean deactivate(int id);

    void activate(int id);

    PageResult<DomainReportCategory> findByContainsCategoryLetterUseCase(String category, int page, int size);
    Optional<DomainReportCategory> findByCategoryAndIsInactive(String category);
    Optional<DomainReportCategory> findById(int id);
    PageResult<DomainReportCategory> findAll(int page, int size);

    boolean existsByCategoryAndIsActive(String category);

    boolean existsByCategoryAndIsInactive(String category);
}