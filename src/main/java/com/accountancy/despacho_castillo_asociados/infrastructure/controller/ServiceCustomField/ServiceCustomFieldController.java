package com.accountancy.despacho_castillo_asociados.infrastructure.controller.ServiceCustomField;


import com.accountancy.despacho_castillo_asociados.application.service.ServiceCustomFields.ServiceCustomFieldsService;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.ServiceCustomFields.ServiceCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service-custom-fields")
public class ServiceCustomFieldController {


    @Autowired
    private ServiceCustomFieldsService service;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<ServiceCustomField>>> getServicesCustomFields(@RequestParam (defaultValue = "0")
                                                                                                     int serviceId,
                                                                                                 @RequestParam (defaultValue = "0")
                                                                                                    int page,
                                                                                                 @RequestParam (defaultValue = "10")
                                                                                                     int size) {
        PageResult<ServiceCustomField> result = service.findServicesCustomFields(serviceId, page, size);

        if (result == null || result.content().isEmpty()) {
            ApiResponse<PageResult<ServiceCustomField>> response = new ApiResponse<>(false, "No service custom " +
                    "fields found", null);
            return ResponseEntity.ok(response);
        }

        ApiResponse<PageResult<ServiceCustomField>> response = new ApiResponse<>(true, "Service custom " +
                "fields retrieved successfully", result);
        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<ServiceCustomField>> createServiceCustomField(@RequestBody
                                                                                    ServiceCustomFieldRequest request) {
        ServiceCustomField createdField = service.createServiceCustomField(request);
        ApiResponse<ServiceCustomField> response = new ApiResponse<>(true, "Service custom field created successfully", createdField);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateServiceCustomField(@PathVariable int id) {
        service.deactiveServiceCustomField(id);
        ApiResponse<Void> response = new ApiResponse<>(true, "Service custom field deactivated successfully", null);
        return ResponseEntity.ok(response);
    }

}
