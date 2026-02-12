package com.accountancy.despacho_castillo_asociados.infrastructure.controller.CustomField;


import com.accountancy.despacho_castillo_asociados.application.service.CustomField.CustomFieldService;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.CustomField.CustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
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

    private Messages messages;

    public CustomFieldController(Messages messages) {
        this.messages = messages;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<CustomField>>> getCustomFields(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size) {

        PageResult<CustomField> customFields = customFieldService.findAllCustomFields(page, size);


        if (customFields == null || customFields.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, messages.get("customfield.exception.fetch.all.none"), null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, messages.get("customfield.success.fetch_all"), customFields)
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> getCustomFieldById(@PathVariable  int id) {
        CustomField customField = customFieldService.findByIdCustomField(id);
        if (customField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, messages.get("customfield.success.fetch_by_id"), customField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, messages.get("customfield.exception.fetch_by_id.notfound", new Object[]{id}), null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomField>> createCustomField(@RequestBody CustomFieldRequest customField) {
        CustomField createdCustomField = customFieldService.createCustomField(customField);
        if (createdCustomField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, messages.get("customfield.success.create"), createdCustomField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, messages.get("customfield.exception.create.failed"), null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomField>> updateCustomField(@RequestBody CustomFieldRequest customField, @PathVariable int id) {
        CustomField updatedCustomField = customFieldService.updateCustomField(customField, id);
        if (updatedCustomField != null) {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(true, messages.get("customfield.success.update"), updatedCustomField)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<CustomField>(false, messages.get("customfield.exception.update.failed"), null)
            );
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deactivateCustomField(@PathVariable int id) {
        customFieldService.deactiveCustomField(id);
        return  ResponseEntity.ok(
                new ApiResponse<Boolean>(true, "customfield.success.deactive", true)
        );
    }


}
