package com.accountancy.despacho_castillo_asociados.infrastructure.config.Report;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.CreateReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.DeactiveReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.FindByIdReportUseCase;
import com.accountancy.despacho_castillo_asociados.application.usecase.Report.FindReportsUseCase;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.*;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportUseCaseConfig {

    @Bean
    public CreateReportUseCase createReportUseCase(ReportRepository reportRepository, Messages messages) {
        return new CreateReportUseCase(reportRepository, messages);
    }

    @Bean
    public FindReportsUseCase findReportsUseCase(ReportRepository reportRepository, Messages messages) {
        return new FindReportsUseCase(reportRepository, messages);
    }

    @Bean
    public DeactiveReportUseCase deactiveReportUseCase(ReportRepository reportRepository, Messages messages) {
        return new DeactiveReportUseCase(reportRepository, messages);
    }

    @Bean
    public FindByIdReportUseCase findByIdReportUseCase(ReportRepository reportRepository, Messages messages) {
        return new FindByIdReportUseCase(reportRepository, messages);
    }
}
