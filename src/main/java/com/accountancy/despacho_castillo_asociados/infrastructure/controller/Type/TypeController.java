package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Type;


import com.accountancy.despacho_castillo_asociados.application.service.Type.TypeService;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.Type;
import com.accountancy.despacho_castillo_asociados.domain.model.Type.TypeRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService serviceType;
    private final Messages messages;

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Tipos')")
    public ResponseEntity<ApiResponse<PageResult<Type>>> getAllTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Type> types = serviceType.findAllType(page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.fetch_all"),
                        types
                )
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_un_Tipo')")
    public ResponseEntity<ApiResponse<Type>> findById(@PathVariable int id) {

        Type type = serviceType.findByIdType(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.fetch_by_id"),
                        type
                )
        );
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('Buscar_Tipo_por_Nombre')")
    public ResponseEntity<ApiResponse<Type>> findByName(@PathVariable String name) {

        Type type = serviceType.findByNameType(name);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.fetch_by_name_like"),
                        type
                )
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Tipo')")
    public ResponseEntity<ApiResponse<Type>> createType(
            @RequestBody  TypeRequest request) {

        Type createdType = serviceType.createType(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.create"),
                        createdType
                )
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Tipo')")
    public ResponseEntity<ApiResponse<Type>> updateType(
            @RequestBody TypeRequest request,
            @PathVariable int id) {

        Type updatedType = serviceType.updateType(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.update"),
                        updatedType
                )
        );
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('Desactivar_Tipo')")
    public ResponseEntity<ApiResponse<Void>> deactivateType(@PathVariable int id) {

        serviceType.deactiveType(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        messages.get("type.success.deactive"),
                        null
                )
        );
    }
}