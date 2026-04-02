package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Permission;

import com.accountancy.despacho_castillo_asociados.application.service.Permission.PermissionService;
import com.accountancy.despacho_castillo_asociados.domain.model.Permission.Permission;
import com.accountancy.despacho_castillo_asociados.domain.model.Permission.PermissionRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Permisos')")
    public ResponseEntity<ApiResponse<PageResult<Permission>>> getAllPermissions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Permission> permissions = permissionService.findAllPermissions(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Permissions retrieved successfully", permissions)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_un_Permiso')")
    public ResponseEntity<ApiResponse<Permission>> findById(@PathVariable int id) {
        Permission permission = permissionService.findByIdPermission(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission found", permission)
        );
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('Buscar_Permiso_por_Nombre')")
    public ResponseEntity<ApiResponse<Permission>> findByName(@PathVariable String name) {
        Permission permission = permissionService.findByNamePermission(name);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission found", permission)
        );
    }

    @GetMapping("/description/{description}")
    @PreAuthorize("hasAuthority('Buscar_Permiso_por_Descripcion')")
    public ResponseEntity<ApiResponse<Permission>> findByDescription(@PathVariable String description) {
        Permission permission = permissionService.findByDescriptionPermission(description);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission found", permission)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Permiso')")
    public ResponseEntity<ApiResponse<Permission>> updatePermission(
            @RequestBody PermissionRequest request,
            @PathVariable int id) {
        Permission updatedPermission = permissionService.updatePermission(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission updated successfully", updatedPermission)
        );
    }

}

