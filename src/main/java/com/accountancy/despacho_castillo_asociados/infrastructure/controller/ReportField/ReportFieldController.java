package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ReportField;


import com.accountancy.despacho_castillo_asociados.application.service.ReportField.ReportFieldService;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportField.ReportFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report-fields")
@RequiredArgsConstructor
public class ReportFieldController {

    private final ReportFieldService reportFieldService;
    private final Messages messages;


    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Campos_Reporte')")
    public ResponseEntity<ApiResponse<PageResult<ReportField>>> getReportFields(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (defaultValue = "0") int reportCategoryId
    ) {

        PageResult<ReportField> reportFields = reportFieldService.findReportsFields(page, size, reportCategoryId);

        return ResponseEntity.ok().body(
                new ApiResponse<>(
                        true,
                        messages.get("reportField.success.fetch_all"),
                        reportFields
                )
        );

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_un_Campo_Reporte')")
    public ResponseEntity<ApiResponse<ReportField>> getReportFieldById(@PathVariable int id) {

        ReportField reportField = reportFieldService.findByIdReportField(id);

        return ResponseEntity.ok().body(
                new ApiResponse<>(
                        true,
                        messages.get("reportField.success.fetch_by_id"),
                        reportField
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Campo_Reporte')")
    public ResponseEntity<ApiResponse<ReportField>> createReportField(@RequestBody ReportFieldRequest reportFieldRequest) {

        ReportField reportField = reportFieldService.createReportField(reportFieldRequest);

        return ResponseEntity.ok().body(
                new ApiResponse<>(
                        true,
                        messages.get("reportField.success.create"),
                        reportField
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Campo_Reporte')")
    public ResponseEntity<ApiResponse<ReportField>> updateReportField(@RequestBody ReportFieldRequest
                                                                                    reportFieldRequest, @PathVariable int id) {

            ReportField reportField = reportFieldService.updateReportField(reportFieldRequest, id);

            return ResponseEntity.ok().body(
                    new ApiResponse<>(
                            true,
                            messages.get("reportField.success.update"),
                            reportField
                    )
            );
        }

    @PatchMapping("deactivate/{id}")
    @PreAuthorize("hasAuthority('Desactivar_Campo_Reporte')")
    public ResponseEntity<ApiResponse<Void>> deactiveReportField(@PathVariable int id) {

        reportFieldService.deactiveReportField(id);

        return ResponseEntity.ok().body(
                new ApiResponse<>(
                        true,
                        messages.get("reportField.success.deactive"),
                        null
                )
        );

    }

}
