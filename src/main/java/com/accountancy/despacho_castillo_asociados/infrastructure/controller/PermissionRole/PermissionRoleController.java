package com.accountancy.despacho_castillo_asociados.infrastructure.controller.PermissionRole;

import com.accountancy.despacho_castillo_asociados.application.service.PermissionRole.PermissionRoleService;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRole;
import com.accountancy.despacho_castillo_asociados.domain.model.PermissionRole.PermissionRoleRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission-roles")
@RequiredArgsConstructor
public class PermissionRoleController {

    private final PermissionRoleService permissionRoleService;

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Permisos_Rol')")
    public ResponseEntity<ApiResponse<PageResult<PermissionRole>>> getAllPermissionRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<PermissionRole> permissionRoles = permissionRoleService.findAllPermissionRoles(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Permission roles retrieved successfully", permissionRoles)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_Permiso_Rol')")
    public ResponseEntity<ApiResponse<PermissionRole>> findById(@PathVariable int id) {
        PermissionRole permissionRole = permissionRoleService.findByIdPermissionRole(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission role found", permissionRole)
        );
    }

    @GetMapping("/role/{roleId}")
    @PreAuthorize("hasAuthority('Buscar_Permisos_por_Rol')")
    public ResponseEntity<ApiResponse<PageResult<PermissionRole>>> findByRoleId(
            @PathVariable int roleId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<PermissionRole> permissionRoles = permissionRoleService.findByIdRolePermissionRole(roleId, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission roles found by role", permissionRoles)
        );
    }

    @GetMapping("/permission/{permissionId}")
    @PreAuthorize("hasAuthority('Buscar_Permisos_Role_por_ID')")
    public ResponseEntity<ApiResponse<PageResult<PermissionRole>>> findByPermissionId(
            @PathVariable int permissionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<PermissionRole> permissionRoles = permissionRoleService.findByPermissionIdPermissionRole(permissionId, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission roles found by permission", permissionRoles)
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Permiso_Rol')")
    public ResponseEntity<ApiResponse<PermissionRole>> createPermissionRole(@RequestBody PermissionRoleRequest request) {
        PermissionRole createdPermissionRole = permissionRoleService.createPermissionRole(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Permission role created successfully", createdPermissionRole)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('Eliminar_Permiso_Rol')")
    public ResponseEntity<ApiResponse<Void>> deletePermissionRole(@PathVariable int id) {
        permissionRoleService.deletePermissionRole(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Permission role deleted successfully", null)
        );
    }

}

