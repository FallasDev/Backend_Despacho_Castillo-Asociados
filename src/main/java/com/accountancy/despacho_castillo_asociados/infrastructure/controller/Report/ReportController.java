package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Report;

import com.accountancy.despacho_castillo_asociados.application.service.Report.ReportService;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    private Messages messages;

    public ReportController(Messages messages) {
        this.messages = messages;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Report>>> findReports(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Report> report = reportService.findReport(title, page, size);

        if (report == null || report.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, messages.get("report.exception.fetch.all.none"), null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, messages.get("report.success.fetch_all"), report)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Report>> findById(@PathVariable int id) {

        Report report = reportService.findByIdReport(id);

        if (report != null) {
            return ResponseEntity.ok(
                    new ApiResponse<Report>(true, "report.exception.fetch.by_id.notfound", report)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<Report>(false, "report.success.fetch_by_id", null)
            );
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Report>> createReport(@RequestBody ReportRequest request) {

        Report createdReport = reportService.createReport(request);

        if (createdReport == null) {
            return ResponseEntity.ok(
                    new ApiResponse<Report>(false, "report.exception.create.failed", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<Report>(true, "report.success.create", createdReport)
        );
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactiveReport(@PathVariable int id) {

        reportService.deactiveReport(id);

        return ResponseEntity.ok(
                new ApiResponse<Void>(true, "report.success.deactive", null)
        );
    }

}
