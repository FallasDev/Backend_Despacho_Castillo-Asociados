package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Service;


import com.accountancy.despacho_castillo_asociados.application.service.Service.DomainServiceService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.DomainService;
import com.accountancy.despacho_castillo_asociados.domain.model.Service.ServiceRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.LocalizedMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final DomainServiceService domainServiceService;
    private final Messages messages;

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Servicios')")
    public ResponseEntity<ApiResponse<PageResult<DomainService>>> getAllServices(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<DomainService> result = domainServiceService.findServices(name, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.fetch_all"),
                        result
                )
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_un_Servicio')")
    public ResponseEntity<ApiResponse<DomainService>> findById(@PathVariable int id) {

        DomainService service = domainServiceService.findByIdService(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.fetch_by_id"),
                        service
                )
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DomainService>>> getAllServices() {

        List<DomainService> services = domainServiceService.findAllWithoutPagination();

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.fetch_all"),
                        services
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Servicio')")
    public ResponseEntity<ApiResponse<DomainService>> createService(@RequestBody ServiceRequest request) {

        DomainService created = domainServiceService.createService(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.create"),
                        created
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Servicio')")
    public ResponseEntity<ApiResponse<DomainService>> updateService(
            @RequestBody ServiceRequest request,
            @PathVariable int id) {

        DomainService updated = domainServiceService.updateService(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.update"),
                        updated
                )
        );
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('Desactivar_Servicio')")
    public ResponseEntity<ApiResponse<Void>> deactivateService(@PathVariable int id) {

        domainServiceService.deactiveService(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("service.success.deactive"),
                        null
                )
        );
    }
}