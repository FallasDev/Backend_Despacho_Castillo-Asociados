package com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;

public class FindReportsFieldValuesUseCase {

    private final ReportFieldValueRepository repository;
    private final Messages messages;

    public FindReportsFieldValuesUseCase(ReportFieldValueRepository repository, Messages messages) {
        this.repository = repository;
        this.messages = messages;
    }

    public PageResult<ReportFieldValue> execute(int reportId, int page, int size) {
        if (reportId > 0) {
            PageResult<ReportFieldValue> fieldValuesByReportId = repository.findByReport(reportId, page, size);
            if (fieldValuesByReportId.content().isEmpty()) {
                throw new EmptyListException(messages.get("reportfieldvalue.exception.fetch.by_report_id.notfound", new Object[]{reportId}));
            }
            return fieldValuesByReportId;
        }

        PageResult<ReportFieldValue> allFieldValues = repository.findAll(page, size);
        if (allFieldValues.content().isEmpty()) {
            throw new EmptyListException(messages.get("reportfieldvalue.exception.fetch.all.none"));
        }
        return allFieldValues;
    }

}
