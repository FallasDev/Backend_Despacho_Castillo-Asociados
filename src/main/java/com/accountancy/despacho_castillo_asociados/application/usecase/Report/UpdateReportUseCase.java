package com.accountancy.despacho_castillo_asociados.application.usecase.Report;

import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.repository.Report.ReportRepository;
import com.accountancy.despacho_castillo_asociados.domain.repository.ReportCategory.ReportCategoryRepository;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import jdk.jfr.Category;

public class UpdateReportUseCase {

    private ReportRepository repository;
    private ReportCategoryRepository categoryRepository;
    private Messages messages;

    public UpdateReportUseCase(ReportRepository repository, ReportCategoryRepository categoryRepository, Messages messages) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.messages = messages;
    }

    public Report execute(int id, ReportRequest report) {

        if (report == null) {
            throw new BadRequestException(messages.get("report.exception.update.cannot_be_null"));
        }

        Report existingReport = repository.findById(id).orElse(null);

        ReportCategory category = categoryRepository.findById(report.getCategoryId()).orElse(null);

        if (category == null || !category.isActive()) {
            throw new BadRequestException(messages.get("report.exception.update.category.not_found",
                        new Object[]{report.getCategoryId()}));
        }

        if (existingReport == null || !existingReport.isActive()) {
            throw new BadRequestException(messages.get("report.exception.update.notfound", new Object[]{id}));
        }

        Report updatedReport = repository.update(report,id , category);

        if (updatedReport == null) {
            throw new BadRequestException(messages.get("report.exception.update.failed"));
        }

        return updatedReport;

    }


}
