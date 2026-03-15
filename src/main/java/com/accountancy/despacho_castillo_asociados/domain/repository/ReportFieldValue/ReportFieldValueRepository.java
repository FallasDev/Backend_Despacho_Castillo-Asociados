package com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;

import java.util.Optional;

public interface ReportFieldValueRepository {

    ReportFieldValue create(ReportFieldValueRequest request);

    ReportFieldValue update(ReportFieldValueRequest request, int id);

    boolean deactive(int id);

    void activate(int id);

    PageResult<ReportFieldValue> findAll(int page, int size);
    Optional<ReportFieldValue> findById(int id);

    PageResult<ReportFieldValue> findByReport(int reportId, int page, int size);
    Optional<ReportFieldValue> findByReportAndField(int reportId, int fieldId);
    PageResult<ReportFieldValue> findByReportAndIsActive(int reportId);

    boolean existsById(int id);
    boolean existsByReport(int reportId);
    boolean existsByReportAndIsActive(int reportId);

    void updateFilePath(int reportFieldValueId, String filePath);


}
