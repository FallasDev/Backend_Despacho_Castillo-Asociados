package com.accountancy.despacho_castillo_asociados.domain.repository.Report;



import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportRepository {

    Report create(ReportRequest reportRequest, ReportCategory category);

    Report update(ReportRequest reportRequest, int id, ReportCategory category);

    boolean deactivate(int id);

    void activate(int id);

    Optional<Report> findById(int id);
    Optional<Report> findByTitleAndIsActive(String title);
    Optional<Report> findByTitleAndIsInactive(String title);

    Optional<Report> findByTitle(String title);

    PageResult<Report> findByContainsTitle(String title, int page, int size);

    PageResult<Report> findAll(int page, int size);
    PageResult<Report> findByContainsTitleLetterUseCase(String name, int page, int size);

    boolean existsByTitleAndIsActive(String title);

    boolean existsByTitleAndIsInactive(String title);
}
