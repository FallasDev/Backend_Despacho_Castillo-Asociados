package com.accountancy.despacho_castillo_asociados.infrastructure.controller.CustomField;


import com.accountancy.despacho_castillo_asociados.application.service.CustomField.CustomFieldService;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/custom-fields")
public class CustomFieldController {

    @Autowired
    private CustomFieldService customFieldService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<CustomField>>> getCustomFields(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size) {

        PageResult<CustomField> customFields = customFieldService.findAllCustomFields(page, size);


        if (customFields == null || customFields.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, "No custom fields found", null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Custom fields retrieved successfully", customFields)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> getCustomFieldById(@PathVariable  int id) {
        CustomField customField = customFieldService.findByIdCustomField(id);
        if (customField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, "Custom field found", customField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, "Custom field not found", null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomField>> createCustomField(@RequestBody CustomFieldRequest customField) {
        CustomField createdCustomField = customFieldService.createCustomField(customField);
        if (createdCustomField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, "Custom field created successfully", createdCustomField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, "Failed to create custom field", null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> updateCustomField(@RequestBody CustomFieldRequest customField, @PathVariable int id) {
        CustomField updatedCustomField = customFieldService.updateCustomField(customField, id);
        if (updatedCustomField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, "Custom field updated successfully", updatedCustomField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, "Failed to update custom field", null)
            );
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deactivateCustomField(@PathVariable int id) {
        customFieldService.deactiveCustomField(id);
        return  ResponseEntity.ok(
                new ApiResponse<Boolean>(true, "Custom field deactivated successfully", true)
        );
    }


}
