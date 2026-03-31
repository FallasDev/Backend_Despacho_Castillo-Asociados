package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ServiceCustomField;


import com.accountancy.despacho_castillo_asociados.application.service.ServiceCustomFields.ServiceCustomFieldsService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service-custom-fields")
@RequiredArgsConstructor
public class ServiceCustomFieldController {

    private final ServiceCustomFieldsService service;
    private final Messages messages;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<ServiceCustomField>>> getServicesCustomFields(
            @RequestParam(defaultValue = "0") int serviceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<ServiceCustomField> result =
                service.findServicesCustomFields(serviceId, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("servicecustomfield.success.fetch_all"),
                        result));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceCustomField>> createServiceCustomField(
            @RequestBody ServiceCustomFieldRequest request) {


        System.out.println("Received request: " + request);

        ServiceCustomField createdFields =
                service.createServiceCustomField(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,
                        messages.get("servicecustomfield.success.create"),
                        createdFields));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ServiceCustomField>> updateServiceCustomField(
            @PathVariable int id,
            @RequestBody ServiceCustomFieldRequest request) {

        System.out.println("Received update request for ID " + id + ": " + request);
        ServiceCustomField updatedFields =
                service.updateServiceCustomField(id,request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(true,
                        messages.get("servicecustomfield.success.update"),
                        updatedFields));
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateServiceCustomField(
            @PathVariable int id) {

        service.deactiveServiceCustomField(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("servicecustomfield.success.deactive"),
                        null));
    }
}