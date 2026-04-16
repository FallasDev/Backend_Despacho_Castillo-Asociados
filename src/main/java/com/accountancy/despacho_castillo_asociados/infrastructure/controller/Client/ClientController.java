package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Client;

import com.accountancy.despacho_castillo_asociados.application.service.Client.ClientService;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.Client;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.ClientResponse;
import com.accountancy.despacho_castillo_asociados.domain.model.Client.UpdateClientRequest;
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
    public ResponseEntity<ApiResponse<PageResult<ClientResponse>>> getAllClients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PageResult<ClientResponse> clients = clientService.findAllClients(page, size);

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Clients retrieved successfully", clients)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponse>> findById(@PathVariable int id) {
        ClientResponse client = toResponse(clientService.findByIdClient(id));

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<ClientResponse>> findByName(@PathVariable String name) {
        ClientResponse client = toResponse(clientService.findByNameClient(name));

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @GetMapping("/surname/{surname}")
    public ResponseEntity<ApiResponse<ClientResponse>> findBySurname(@PathVariable String surname) {
        ClientResponse client = toResponse(clientService.findBySurnameClient(surname));

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Client found", client)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientResponse>> createClient(@RequestBody ClientRequest request) throws MessagingException {
        ClientResponse createdClient = toResponse(clientService.createClient(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(true, "Client created successfully", createdClient)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientResponse>> updateClient(
            @RequestBody UpdateClientRequest request,
            @PathVariable int id) {
        ClientResponse updatedClient = toResponse(clientService.updateClient(request, id));

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

    private ClientResponse toResponse(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientResponse(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getPhoneNumber(),
                client.getPersonalId(),
                client.getEmail(),
                client.getCreatedAt(),
                client.isActive()
        );
    }

}
