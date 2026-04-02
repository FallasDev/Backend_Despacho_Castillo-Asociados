package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Role;

import com.accountancy.despacho_castillo_asociados.application.service.Role.RoleService;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.Role;
import com.accountancy.despacho_castillo_asociados.domain.model.Role.RoleRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasAuthority('Obtener_Roles')")
    public ResponseEntity<ApiResponse<PageResult<Role>>> getAllRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Role> roles = roleService.findAllRoles(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Roles retrieved successfully", roles)
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('Obtener_un_Rol')")
    public ResponseEntity<ApiResponse<Role>> findById(@PathVariable int id) {
        Role role = roleService.findByIdRole(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Role found", role)
        );
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('Buscar_Rol_por_Nombre')")
    public ResponseEntity<ApiResponse<Role>> findByName(@PathVariable String name) {
        Role role = roleService.findByNameRole(name);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Role found", role)
        );
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Crear_Rol')")
    public ResponseEntity<ApiResponse<Role>> createRole(@RequestBody RoleRequest request) {
        Role createdRole = roleService.createRole(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Role created successfully", createdRole)
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('Actualizar_Rol')")
    public ResponseEntity<ApiResponse<Role>> updateRole(
            @RequestBody RoleRequest request,
            @PathVariable int id) {
        Role updatedRole = roleService.updateRole(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Role updated successfully", updatedRole)
        );
    }

    @PatchMapping("/deactivate/{id}")
    @PreAuthorize("hasAuthority('Desactivar_Rol')")
    public ResponseEntity<ApiResponse<Void>> deactivateRole(@PathVariable int id) {
        roleService.deactivateRole(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Role deactivated successfully", null)
        );
    }

}

