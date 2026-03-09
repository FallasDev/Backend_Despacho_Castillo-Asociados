package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Report;

import com.accountancy.despacho_castillo_asociados.application.service.Report.ReportService;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.Report;
import com.accountancy.despacho_castillo_asociados.domain.model.Report.ReportRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {


    private final ReportService reportService;

    private final Messages messages;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Report>>> findReports(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Report> report = reportService.findReport(title, page, size);


        return ResponseEntity.ok().body(
                new ApiResponse<>(true, messages.get("report.success.fetch_all"), report)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Report>> findById(@PathVariable int id) {

        Report report = reportService.findByIdReport(id);

        return ResponseEntity.ok(
                new ApiResponse<Report>(true, messages.get("report.success.fetch_by_id"), report)
        );

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Report>> createReport(@RequestBody ReportRequest request) {

        Report createdReport = reportService.createReport(request);

        return ResponseEntity.ok(
                new ApiResponse<Report>(true, messages.get("report.success.create"), createdReport)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Report>> updateReport(@RequestBody ReportRequest request, @
PathVariable int id) {

        Report updatedReport = reportService.updateReport(request, id);

        return ResponseEntity.ok(
                new ApiResponse<Report>(true, messages.get("report.success.update"), updatedReport)
        );
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactiveReport(@PathVariable int id) {

        reportService.deactiveReport(id);

        return ResponseEntity.ok(
                new ApiResponse<Void>(true, messages.get("report.success.deactive"), null)
        );
    }

}
