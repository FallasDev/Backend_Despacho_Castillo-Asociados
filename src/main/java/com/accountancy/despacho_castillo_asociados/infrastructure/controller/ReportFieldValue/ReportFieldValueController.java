package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ReportFieldValue;


import com.accountancy.despacho_castillo_asociados.application.service.ReportFieldValue.ReportFieldValueService;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValue;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportFieldValue.ReportFieldValueRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/report-field-values")
@RequiredArgsConstructor
public class ReportFieldValueController {

    private final ReportFieldValueService service;
    private final Messages messages;

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Valor_Campo_Reporte')")
    public ResponseEntity<ApiResponse<ReportFieldValue>> createReportFieldValue(
            @RequestBody ReportFieldValueRequest request
    ) {

        ReportFieldValue reportFieldValue = service.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        messages.get("reportfieldvalue.success.create"),
                        reportFieldValue
                )
        );

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Valor_Campo_Reporte')")
    public ResponseEntity<ApiResponse<ReportFieldValue>> updateReportFieldValue(
            @RequestBody ReportFieldValueRequest request,
            @PathVariable int id
    ) {
        ReportFieldValue updatedReportFieldValue = service.update(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("reportfieldvalue.success.update", new Object[]{id}),
                        updatedReportFieldValue
                )
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Valores_Campo_Reporte')")
    public ResponseEntity<ApiResponse<PageResult<ReportFieldValue>>> getReportFieldValues(
            @RequestParam(defaultValue = "0") int reportId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        PageResult<ReportFieldValue> fieldValues = service.findReportsFieldValues(reportId, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("reportfieldvalue.success.fetch_all"),
                        fieldValues
                )
        );
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasAuthority('Desactivar_Valor_Campo_Reporte')")
    public ResponseEntity<ApiResponse<Void>> deactivateReportFieldValue(@PathVariable int id) {
        service.deactive(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("reportfieldvalue.success.deactive", new Object[]{id}),
                        null
                )
        );
    }

    @PostMapping("/{id}/upload")
    @PreAuthorize("hasAuthority('Subir_Archivo_Valor_Campo_Reporte')")
    public ResponseEntity<ApiResponse<Void>> uploadFile(
            @PathVariable int id,
            @RequestParam MultipartFile file
    ) throws IOException {

        service.uploadFile(
            id,
            file,
            file.getOriginalFilename()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("reportfieldvalue.success.file_upload", new Object[]{id}),
                        null
                )
        );

    }

}
