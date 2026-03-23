package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Client;

import com.accountancy.despacho_castillo_asociados.application.service.Client.ClientService;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Client>>> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<Client> clients = clientService.findAllClients(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Clients retrieved successfully", clients)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> findById(@PathVariable int id) {
        Client client = clientService.findByIdClient(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<Client>> findByName(@PathVariable String name) {
        Client client = clientService.findByNameClient(name);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<ApiResponse<Client>> findBySurname(@PathVariable String surname) {
        Client client = clientService.findBySurnameClient(surname);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Client>> createClient(@RequestBody ClientRequest request) throws MessagingException {
        Client createdClient = clientService.createClient(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Client created successfully", createdClient)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClient(
            @RequestBody ClientRequest request,
            @PathVariable int id) {
        Client updatedClient = clientService.updateClient(request, id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client updated successfully", updatedClient)
        );
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<ApiResponse<Void>> deactivateClient(@PathVariable int id) {
        clientService.deactivateClient(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client deactivated successfully", null)
        );
    }

}

