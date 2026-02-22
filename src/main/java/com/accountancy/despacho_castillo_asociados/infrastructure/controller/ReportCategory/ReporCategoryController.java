package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ReportCategory;


import com.accountancy.despacho_castillo_asociados.application.service.ReportCategory.ReportCategoryService;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/reportCategory")
public class ReporCategoryController {

    @Autowired
    private ReportCategoryService reportCategoryService;


    private Messages messages;

    public ReporCategoryController(Messages messages) {
        this.messages = messages;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<ReportCategory>>> getAllReportsCategory(@RequestParam (required = false) String category,
                                                                                 @RequestParam (defaultValue = "0") int page,
                                                                                 @RequestParam (defaultValue = "10") int size) {

        PageResult<ReportCategory> reportCategory = reportCategoryService.findByContainsCategoryLetterUseCase(category, page, size);

        if (reportCategory == null || reportCategory.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, messages.get("report.exception.fetch.all.none"), null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, messages.get("reportCategory.success.fetch_all"), reportCategory)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportCategory>> findById(@PathVariable  int id
    ) {
        ReportCategory reportCategory = reportCategoryService.findByIdReportCategory(id);
        if (reportCategoryService != null) {
            return ResponseEntity.ok(
                    new ApiResponse<ReportCategory>(true, "reportCategory.exception.fetch.by_id.notfound", reportCategory)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<ReportCategory>(false, "reportCategory.success.fetch_by_id", null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ReportCategory>> createReportCategory(@RequestBody ReportCategoryRequest request) {
        ReportCategory createdReportCategory = reportCategoryService.createReportCategory(request);

        if (createdReportCategory == null) {
            return ResponseEntity.ok(
                    new ApiResponse<ReportCategory>(false, "reportCategory.exception.create.failed", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<ReportCategory>(true, "reportCategory.success.create", createdReportCategory)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportCategory>> updateReport(@RequestBody ReportCategoryRequest request
            , @PathVariable int id) {
        ReportCategory updateReportCategory = reportCategoryService.updateReportCategory(request, id);

        if (updateReportCategory == null) {
            return ResponseEntity.ok(
                    new ApiResponse<ReportCategory>(false, "reportCategory.exception.update.failed", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<ReportCategory>(true, "reportCategory.success.update", updateReportCategory)
        );
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateReport(@PathVariable int id) {
        reportCategoryService.deactiveReportCategory(id);
        return ResponseEntity.ok(
                new ApiResponse<Void>(true, "reportCategory.success.deactive", null)
        );
    }







}
