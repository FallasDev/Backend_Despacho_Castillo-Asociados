package com.accountancy.despacho_castillo_asociados.domain.repository.ReportField;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportFieldRepository {

    ReportField create(ReportFieldRequest request, Type type, ReportCategory reportCategory);

    ReportField update(ReportFieldRequest request, int id, Type type, ReportCategory reportCategory);

    boolean deactive(int id);

    void activate(int id);

    PageResult<ReportField> findAll(int page, int size);
    Optional<ReportField> findById(int id);
    Optional<ReportField> findByLabelAndIsInactive(String label);

    PageResult<ReportField> findByReportCategory(int page, int size,int reportCategoryId);

    boolean existsById(int id);
    boolean existsByLabel(String label);
    boolean existsByLabelAndIsActive(String label);

}
