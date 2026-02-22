package com.accountancy.despacho_castillo_asociados.infrastructure.config.ReportCategory;
import com.accountancy.despacho_castillo_asociados.application.usecase.ReportCategory.*;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportCategoryUseCaseConfig {

    @Bean
    public CreateReportCategoryUseCase createReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        return new CreateReportCategoryUseCase(reportCategoryRepository, messages);
    }

    @Bean
    public FindReportsCategoryUseCase findReportsCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        return new FindReportsCategoryUseCase(reportCategoryRepository, messages);
    }

    @Bean
    public DeactiveReportCategoryUseCase deactiveReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        return new DeactiveReportCategoryUseCase(reportCategoryRepository, messages);
    }

    @Bean
    public UpdateReportCategoryUseCase updateReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        return new UpdateReportCategoryUseCase(reportCategoryRepository, messages);
    }

    @Bean
    public FindByIdReportCategoryUseCase findByIdReportCategoryUseCase(ReportCategoryRepository reportCategoryRepository, Messages messages) {
        return new FindByIdReportCategoryUseCase(reportCategoryRepository, messages);
    }
}
