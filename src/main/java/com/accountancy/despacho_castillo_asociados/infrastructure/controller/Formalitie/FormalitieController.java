package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Formalitie;


import com.accountancy.despacho_castillo_asociados.application.service.Formalitie.FormalitieService;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.Messages;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/formalities")
@RequiredArgsConstructor
public class FormalitieController {

    private final FormalitieService formalitieService;
    private final Messages messages;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Formalitie>>> getFormalities(
            @RequestParam(required = false) String serviceName,
            @RequestParam(required = false) Integer state,
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateFrom,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate dateTo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        if (dateFrom != null && dateTo != null && dateFrom.isAfter(dateTo)) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false,
                            "Invalid date range: 'dateFrom' cannot be after 'dateTo'",
                            null));
        }

        SearchFormalitie search = new SearchFormalitie();
        search.setServiceName(serviceName);
        search.setStateId(state);
        search.setClientName(clientName);
        search.setUserName(userName);
        search.setStartDate(dateFrom != null ? dateFrom.atStartOfDay() : null);
        search.setEndDate(dateTo != null ? dateTo.atTime(23,59,59) : null);

        PageResult<Formalitie> result =
                formalitieService.findFormalities(search, page, size);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("formality.success.fetch_all"),
                        result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Formalitie>> findById(@PathVariable int id) {

        Formalitie formalitie =
                formalitieService.findFormalitieById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("formality.success.fetch_by_id"),
                        formalitie));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Formalitie>> createFormalitie(
             @RequestBody FormalitieRequest request) {

        Formalitie created =
                formalitieService.createFormalitie(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true,
                        messages.get("formality.success.create"),
                        created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Formalitie>> updateFormalitie(
            @PathVariable int id,
             @RequestBody FormalitieRequest request) {

        Formalitie updated =
                formalitieService.updateFormalitie(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("formality.success.update"),
                        updated));
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<ApiResponse<Void>> changeFormalitieState(
            @PathVariable int id,
            @RequestParam int newState) {

        formalitieService.changeFormalitieState(id, newState);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("formality.success.change_status"),
                        null));
    }

    @PatchMapping("/{id}/handle")
    public ResponseEntity<ApiResponse<Void>> handleFormalitie(
            @PathVariable int id,
            @RequestParam int userId) {

        formalitieService.handleFormalitie(id, userId);

        return ResponseEntity.ok(
                new ApiResponse<>(true,
                        messages.get("formality.success.handle"),
                        null));
        

    }
}