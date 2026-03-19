package com.accountancy.despacho_castillo_asociados.application.service.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue.*;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ReportFieldValueService {

    private CreateReportFieldValueUseCase createReportFieldValueUseCase;
    private UpdateReportFieldValueUseCase updateReportFieldValueUseCase;
    private DeactiveReportFieldValueUseCase deactiveReportFieldValueUseCase;
    private FindReportsFieldValuesUseCase findReportsFieldValuesUseCase;
    private UploadFileReportFieldValueUseCase uploadFileReportFieldValueUseCase;

    public ReportFieldValueService(
        CreateReportFieldValueUseCase createReportFieldValueUseCase,
        UpdateReportFieldValueUseCase updateReportFieldValueUseCase,
        DeactiveReportFieldValueUseCase deactiveReportFieldValueUseCase,
        FindReportsFieldValuesUseCase findReportsFieldValuesUseCase,
        UploadFileReportFieldValueUseCase uploadFileReportFieldValueUseCase
    ) {
        this.createReportFieldValueUseCase = createReportFieldValueUseCase;
        this.updateReportFieldValueUseCase = updateReportFieldValueUseCase;
        this.deactiveReportFieldValueUseCase = deactiveReportFieldValueUseCase;
        this.findReportsFieldValuesUseCase = findReportsFieldValuesUseCase;
        this.uploadFileReportFieldValueUseCase = uploadFileReportFieldValueUseCase;
    }

     public ReportFieldValue create(ReportFieldValueRequest request) {
        return createReportFieldValueUseCase.execute(request);
    }

    public ReportFieldValue update(ReportFieldValueRequest request, int id) {
        return updateReportFieldValueUseCase.execute(request, id);
    }

    public void deactive(int id) {
        deactiveReportFieldValueUseCase.execute(id);
    }

    public PageResult<ReportFieldValue> findReportsFieldValues(int reportId, int page, int size) {
        return findReportsFieldValuesUseCase.execute(reportId, page, size);
    }

    public boolean uploadFile(int reportFieldValueId, MultipartFile file, String filename) throws IOException {
        return uploadFileReportFieldValueUseCase.execute(reportFieldValueId, file , filename);
    }

}
