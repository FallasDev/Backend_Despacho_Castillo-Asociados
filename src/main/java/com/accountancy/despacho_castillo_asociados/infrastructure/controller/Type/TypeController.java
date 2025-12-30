package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Type;


import com.accountancy.despacho_castillo_asociados.application.service.Type.TypeService;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService serviceType;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Type>>> getAllTypes(@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size) {

        PageResult<Type> types = serviceType.findAllType(page, size);


        if (types == null || types.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, "No types found", null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Types retrieved successfully", types)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>> findById(@PathVariable  int id) {
        Type type = serviceType.findByIdType(id);
        if (type != null) {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(true, "Type found", type)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(false, "Type not found", null
            ));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Type>> findByName(@PathVariable String name) {
        Type type = serviceType.findByNameType(name);

        if (type != null) {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(true, "Type found", type)
             );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(false, "Type not found", null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Type>> createType(@RequestBody TypeRequest type) {
        Type createdType = serviceType.createType(type);

        if (createdType == null) {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(false, "Type could not be created", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<Type>(true, "Type created successfully", createdType)
        );


    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Type>>  updateType(@RequestBody TypeRequest type, @PathVariable int id
    ) {
        Type updatedType = serviceType.updateType(type, id);

        if (updatedType == null) {
            return ResponseEntity.ok(
                    new ApiResponse<Type>(false, "Type could not be updated", null)
            );
        }

        return ResponseEntity.ok(
                new ApiResponse<Type>(true, "Type updated successfully", updatedType)
        );
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateType(@PathVariable int id) {
        serviceType.deactiveType(id);
        return ResponseEntity.ok(
                new ApiResponse<Void>(true, "Type deactivated successfully", null)
        );
    }





}
