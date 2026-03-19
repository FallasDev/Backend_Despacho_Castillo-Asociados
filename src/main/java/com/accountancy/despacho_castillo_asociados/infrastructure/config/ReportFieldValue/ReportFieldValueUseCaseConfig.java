package com.accountancy.despacho_castillo_asociados.infrastructure.config.ReportFieldValue;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportFieldValue.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportFieldValue.ReportFieldValueRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.UploadFile.UploadFileRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportFieldValueUseCaseConfig {

    @Bean
    public CreateReportFieldValueUseCase createReportFieldValueUseCase(ReportFieldValueRepository reportFieldValueRepository,
                                                                       ReportFieldRepository reportFieldRepository,
                                                                       ReportRepository reportRepository,
                                                                       Messages messages) {
        return new CreateReportFieldValueUseCase(reportFieldValueRepository, reportFieldRepository, reportRepository, messages);
    }

    @Bean
    public UpdateReportFieldValueUseCase updateReportFieldValueUseCase(ReportFieldValueRepository repository, Messages messages) {
        return new UpdateReportFieldValueUseCase(repository, messages);
    }

    @Bean
    public DeactiveReportFieldValueUseCase deactiveReportFieldValueUseCase(ReportFieldValueRepository repository, Messages messages) {
        return new DeactiveReportFieldValueUseCase(repository, messages);
    }

    @Bean
    public FindReportsFieldValuesUseCase findReportsFieldValuesUseCase(ReportFieldValueRepository repository, Messages messages) {
        return new FindReportsFieldValuesUseCase(repository, messages);
    }

    @Bean
    public UploadFileReportFieldValueUseCase uploadFileReportFieldValueUseCase(ReportFieldValueRepository reportFieldValueRepository, UploadFileRepository uploadFileRepository) {
        return new UploadFileReportFieldValueUseCase(uploadFileRepository,reportFieldValueRepository);
    }
}
