package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ReportCategory;


import com.accountancy.despacho_castillo_asociados.application.service.ReportCategory.ReportCategoryService;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategory;
import com.accountancy.despacho_castillo_asociados.domain.model.ReportCategory.ReportCategoryRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reportCategory")
@RequiredArgsConstructor
public class ReportCategoryController {


    private final ReportCategoryService reportCategoryService;
    private final Messages messages;


    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Categorias_Reporte')")
    public ResponseEntity<ApiResponse<PageResult<ReportCategory>>> getAllReportsCategory(@RequestParam (required = false) String category,
                                                                                 @RequestParam (defaultValue = "0") int page,
                                                                                 @RequestParam (defaultValue = "10") int size) {

        PageResult<ReportCategory> reportCategory = reportCategoryService.findByContainsCategoryLetterUseCase(category,
                page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, messages.get("reportCategory.success.fetch_all"), reportCategory)
        );

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_una_Categoria_Reporte')")
    public ResponseEntity<ApiResponse<ReportCategory>> findById(@PathVariable  int id
    ) {
        ReportCategory reportCategory = reportCategoryService.findByIdReportCategory(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, messages.get("reportCategory.success.fetch.by_id"),
                        reportCategory)
        );

    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Categoria_Reporte')")
    public ResponseEntity<ApiResponse<ReportCategory>> createReportCategory(@RequestBody ReportCategoryRequest request) {
        ReportCategory createdReportCategory = reportCategoryService.createReportCategory(request);

        return ResponseEntity.ok(
                new ApiResponse<ReportCategory>(true, messages.get("reportCategory.success.create"), createdReportCategory)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Categoria_Reporte')")
    public ResponseEntity<ApiResponse<ReportCategory>> updateReport(@RequestBody ReportCategoryRequest request
            , @PathVariable int id) {
        ReportCategory updateReportCategory = reportCategoryService.updateReportCategory(request, id);

        return ResponseEntity.ok(
                new ApiResponse<ReportCategory>(true, messages.get("reportCategory.success.update"), updateReportCategory)
        );
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('Desactivar_Categoria_Reporte')")
    public ResponseEntity<ApiResponse<Void>> deactivateReport(@PathVariable int id) {
        reportCategoryService.deactiveReportCategory(id);
        return ResponseEntity.ok(
                new ApiResponse<Void>(true, messages.get("reportCategory.success.deactive"), null)
        );
    }

}
