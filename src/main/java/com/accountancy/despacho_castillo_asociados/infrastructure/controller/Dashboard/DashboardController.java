package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Dashboard;

import com.accountancy.despacho_castillo_asociados.application.service.Dashboard.DashboardService;
import com.accountancy.despacho_castillo_asociados.domain.model.Dashboard.DashboardStats;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStats>> getStats() {
        DashboardStats stats = dashboardService.getStats();
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Dashboard stats retrieved successfully", stats)
        );
    }
}
