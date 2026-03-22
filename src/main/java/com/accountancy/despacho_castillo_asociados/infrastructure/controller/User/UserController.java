package com.accountancy.despacho_castillo_asociados.infrastructure.controller.User;

import com.accountancy.despacho_castillo_asociados.application.service.User.UserService;
import com.accountancy.despacho_castillo_asociados.domain.model.User.User;
import com.accountancy.despacho_castillo_asociados.domain.model.User.UserRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<User>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<User> users = userService.findAllUsers(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Users retrieved successfully", users)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> findById(@PathVariable int id) {
        User user = userService.findByIdUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User found", user)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<User>> findByName(@PathVariable String name) {
        User user = userService.findByNameUser(name);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User found", user)
        );
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<ApiResponse<User>> findBySurname(@PathVariable String surname) {
        User user = userService.findBySurnameUser(surname);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User found", user)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody UserRequest request) {
        User createdUser = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "User created successfully", createdUser)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @RequestBody UserRequest request,
            @PathVariable int id) {
        User updatedUser = userService.updateUser(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User updated successfully", updatedUser)
        );
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateUser(@PathVariable int id) {
        userService.deactivateUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User deactivated successfully", null)
        );
    }

}

