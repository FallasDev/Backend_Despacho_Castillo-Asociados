package com.accountancy.despacho_castillo_asociados.infrastructure.controller.FormalitieCustomFields;

import com.accountancy.despacho_castillo_asociados.application.service.FormalitieCustomFields.FormalitieCustomFieldsService;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomField;
import com.accountancy.despacho_castillo_asociados.domain.model.FormalitieCustomFields.FormalitieCustomFieldRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/formalitie-custom-fields")
@RequiredArgsConstructor
public class FormalitieCustomFieldController {

    private final FormalitieCustomFieldsService formalitieCustomFieldService;
    private final Messages messages;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<FormalitieCustomField>>> getFormalitiesCustomField(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int formalitieId
    ) {

        PageResult<FormalitieCustomField> result =
                formalitieCustomFieldService.find(formalitieId, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("formalitycustomfield.success.fetch_all"),
                        result
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FormalitieCustomField>> createFormalitieCustomField(
            @RequestBody FormalitieCustomFieldRequest request
    ) {

        FormalitieCustomField created =
                formalitieCustomFieldService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        messages.get("formalitycustomfield.success.create"),
                        created
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FormalitieCustomField>> updateFormalitieCustomField(
            @PathVariable int id,
            @RequestBody FormalitieCustomFieldRequest request
    ) {

        System.out.println("Updating FormalitieCustomField with ID: " + id);

        FormalitieCustomField updated =
                formalitieCustomFieldService.update(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("formalitycustomfield.success.update"),
                        updated
                )
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<ApiResponse<Void>> deactivateFormalitieCustomField(
            @PathVariable int id
    ) {

        formalitieCustomFieldService.deactivate(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("formalitycustomfield.success.deactive"),
                        null
                )
        );
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<ApiResponse<Void>> uploadFile(@PathVariable int id,@RequestParam("file") MultipartFile file) throws IOException {

        formalitieCustomFieldService.uploadFile(
                id, // Aquí deberías pasar el ID correcto del FormalitieCustomField
                file,
                file.getOriginalFilename()
        );

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("formalitycustomfield.success.file_upload"),
                        null
                )
        );
    }
}