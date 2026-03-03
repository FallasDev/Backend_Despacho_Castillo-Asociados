package com.accountancy.despacho_castillo_asociados.infrastructure.controller.CustomField;


import com.accountancy.despacho_castillo_asociados.application.service.CustomField.CustomFieldService;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
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
@RequestMapping("/custom-fields")
@RequiredArgsConstructor
public class CustomFieldController {

    private final CustomFieldService customFieldService;
    private final Messages messages;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<CustomField>>> getCustomFields(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<CustomField> result =
                customFieldService.findAllCustomFields(page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("customfield.success.fetch_all"),
                        result
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> getCustomFieldById(
            @PathVariable int id) {

        CustomField customField =
                customFieldService.findByIdCustomField(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("customfield.success.fetch_by_id"),
                        customField
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomField>> createCustomField(
            @RequestBody CustomFieldRequest request) {

        CustomField created =
                customFieldService.createCustomField(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        messages.get("customfield.success.create"),
                        created
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> updateCustomField(
            @RequestBody CustomFieldRequest request,
            @PathVariable int id) {

        CustomField updated =
                customFieldService.updateCustomField(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("customfield.success.update"),
                        updated
                )
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateCustomField(
            @PathVariable int id) {

        customFieldService.deactiveCustomField(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("customfield.success.deactive"),
                        null
                )
        );
    }
}