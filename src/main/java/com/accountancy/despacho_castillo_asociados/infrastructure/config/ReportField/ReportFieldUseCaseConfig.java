package com.accountancy.despacho_castillo_asociados.infrastructure.config.ReportField;

import com.accountancy.despacho_castillo_asociados.application.usecase.ReportField.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportField.ReportFieldRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.Type.TypeRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportFieldUseCaseConfig {

    @Bean
    public CreateReportFieldUseCase createReportFieldUseCase(ReportFieldRepository reportFieldRepository,
                                                             TypeRepository typeRepository,
                                                             ReportCategoryRepository reportCategoryRepository,
                                                             Messages messages) {
        return new CreateReportFieldUseCase(reportFieldRepository,typeRepository, reportCategoryRepository ,messages);
    }

    @Bean
    public DeactiveReportFieldUseCase deactiveReportFieldUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        return new DeactiveReportFieldUseCase(reportFieldRepository, messages);
    }

    @Bean
    public FindByIdReportFieldUseCase findByIdReportFieldUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        return new FindByIdReportFieldUseCase(reportFieldRepository, messages);
    }

    @Bean
    public UpdateReportFieldUseCase updateReportFieldUseCase(ReportFieldRepository reportFieldRepository,
                                                             TypeRepository typeRepository,
                                                             ReportCategoryRepository reportCategoryRepository,
                                                             Messages messages) {
        return new UpdateReportFieldUseCase(reportFieldRepository, typeRepository ,messages, reportCategoryRepository);
    }

    @Bean
    public FindReportsFieldsUseCase findReportsFieldsUseCase(ReportFieldRepository reportFieldRepository, Messages messages) {
        return new FindReportsFieldsUseCase(reportFieldRepository, messages);
    }

}
