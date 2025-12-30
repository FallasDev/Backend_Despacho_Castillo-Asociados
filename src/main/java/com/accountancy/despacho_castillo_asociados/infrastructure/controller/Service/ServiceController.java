package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Service;


import com.accountancy.despacho_castillo_asociados.application.service.Service.DomainServiceService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private DomainServiceService domainServiceService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<DomainService>>> getAllServices(@RequestParam (required = false) String name, @RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size) {


        PageResult<DomainService> domainServices = domainServiceService.findServices(name, page, size);

        if (domainServices == null || domainServices.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, "No services found", null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Services retrieved successfully", domainServices)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DomainService>> findById(@PathVariable  int id
    ) {
        DomainService domainService = domainServiceService.findByIdService(id);
        if (domainService != null) {
            return ResponseEntity.ok(
                    new ApiResponse<DomainService>(true, "Service found", domainService)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<DomainService>(false, "Service not found", null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DomainService>> createService(@RequestBody ServiceRequest request) {
        DomainService createdService = domainServiceService.createService(request);

        if (createdService == null) {
            return ResponseEntity.ok(
                    new ApiResponse<DomainService>(false, "Service could not be created", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<DomainService>(true, "Service created successfully", createdService)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DomainService>> updateService(@RequestBody ServiceRequest request
            , @PathVariable int id) {
        DomainService updatedService = domainServiceService.updateService(request, id);

        if (updatedService == null) {
            return ResponseEntity.ok(
                    new ApiResponse<DomainService>(false, "Service could not be updated", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<DomainService>(true, "Service updated successfully", updatedService)
        );
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateService(@PathVariable int id) {
        domainServiceService.deactiveService(id);
        return ResponseEntity.ok(
                new ApiResponse<Void>(true, "Service deactivated successfully", null)
        );
    }







}
