package com.accountancy.despacho_castillo_asociados.infrastructure.controller.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.application.service.FormalitieCustomFields.FormalitieCustomFieldsService;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/formalitie-custom-fields")
public class FormalitieCustomFieldController {

    @Autowired
    private FormalitieCustomFieldsService formalitieCustomFieldService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<FormalitieCustomField>>> getFormalitiesCustomField(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam (required = false) Integer formalitieId
    ) {

        PageResult<FormalitieCustomField> formalitiesCustomField = formalitieCustomFieldService.find(page, size, formalitieId);
        if (formalitiesCustomField == null || formalitiesCustomField.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, "No formalities custom fields found", null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Formalities custom fields retrieved successfully", formalitiesCustomField)
        );

    }

    @PostMapping
    public ResponseEntity<ApiResponse<FormalitieCustomField>> createFormalitieCustomField(
            @RequestBody FormalitieCustomFieldRequest formalitieCustomField
    ) {
        FormalitieCustomField createdFormalitieCustomField = formalitieCustomFieldService.create(formalitieCustomField);
        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Formalitie custom field created successfully", createdFormalitieCustomField)
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<FormalitieCustomField>> updateFormalitieCustomField(
            @RequestParam int id,
            @RequestBody FormalitieCustomFieldRequest formalitieCustomField
    ) {
        FormalitieCustomField updatedFormalitieCustomField = formalitieCustomFieldService.update(id, formalitieCustomField);
        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Formalitie custom field updated successfully", updatedFormalitieCustomField)
        );
    }

    @PutMapping
    @RequestMapping("/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateFormalitieCustomField(
            @RequestParam int id
    ) {
        formalitieCustomFieldService.deactivate(id);
        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Formalitie custom field deactivated successfully", null)
        );
    }


}
