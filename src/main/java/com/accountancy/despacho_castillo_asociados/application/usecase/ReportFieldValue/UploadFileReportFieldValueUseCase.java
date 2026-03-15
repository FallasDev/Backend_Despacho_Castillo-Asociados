package com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.domain.model.UploadFile.UploadFile;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class UploadFileReportFieldValueUseCase {

    private UploadFileRepository uploadFileRepository;
    private ReportFieldValueRepository reportFieldValueRepository;

    public UploadFileReportFieldValueUseCase(UploadFileRepository uploadFileRepository, ReportFieldValueRepository reportFieldValueRepository) {
        this.uploadFileRepository = uploadFileRepository;
        this.reportFieldValueRepository = reportFieldValueRepository;
    }

    public boolean execute(int reportFieldValueId, MultipartFile file, String filename) throws IOException {
        UploadFile result = uploadFileRepository.uploadFile(reportFieldValueId, file, filename);

        reportFieldValueRepository.updateFilePath(
            reportFieldValueId,
            result.getSecureUrl()
        );

        return true;
    }

}
