package com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory;



import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.DomainReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;

import java.util.Optional;

public interface ReportCategoryRepository {

    DomainReportCategory create(ReportCategoryRequest reportCategoryRequest);

    DomainReportCategory update(ReportCategoryRequest reportCategoryRequest);

    boolean deactivate(int id);

    void activate(int id);

    Optional<DomainReportCategory> findById(int id);
    Optional<DomainReportCategory> findAll(int page, int size);
}