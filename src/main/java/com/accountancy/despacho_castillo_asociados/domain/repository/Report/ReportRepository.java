package com.accountancy.despacho_castillo_asociados.domain.repository.Report;



import com.accountancy.despacho_castillo_asociados.domain.model.Report.DomainReport;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.DomainReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportRepository {

    DomainReport create(ReportRequest reportRequest);

    boolean deactivate(int id);

    void activate(int id);

    Optional<DomainReport> findById(int id);
    Optional<DomainReport> findAll(int page, int size);
}
