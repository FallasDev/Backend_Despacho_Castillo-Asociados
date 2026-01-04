package com.accountancy.despacho_castillo_asociados.infrastructure.controller.Formalitie;


import com.accountancy.despacho_castillo_asociados.application.service.Formalitie.FormalitieService;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.Formalitie;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.FormalitieRequest;
import com.accountancy.despacho_castillo_asociados.domain.model.Formalitie.SearchFormalitie;
import com.accountancy.despacho_castillo_asociados.shared.ApiResponse;
import com.accountancy.despacho_castillo_asociados.shared.PageResult;
import com.accountancy.despacho_castillo_asociados.shared.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/formalities")
public class FormalitieController {


    @Autowired
    private FormalitieService formalitieService;


    @GetMapping
    public ResponseEntity<ApiResponse<PageResult<Formalitie>>> getFormalities(@RequestParam (required = false) String serviceName,
                                                                              @RequestParam (required = false) Integer state,
                                                                              @RequestParam (required = false) String clientName,
                                                                              @RequestParam (required = false) String userName,
                                                                              @RequestParam (required = false) String dateFrom,
                                                                              @RequestParam (required = false) String dateTo,
                                                                              @RequestParam (defaultValue = "0") int page,
                                                                              @RequestParam (defaultValue = "10") int size) {

        if (dateFrom != null && dateTo != null) {
            if (dateFrom.compareTo(dateTo) > 0) {
                return ResponseEntity.badRequest().body(
                        new ApiResponse<>(false, "Invalid date range: " +
                                "'dateFrom' cannot be after 'dateTo'", null)
                );
            }
        }

        SearchFormalitie searchFormalitie = new SearchFormalitie();
        searchFormalitie.setServiceName(serviceName);
        searchFormalitie.setStateId(state != null ? state : -1);
        searchFormalitie.setClientName(clientName);
        searchFormalitie.setUserName(userName);
        searchFormalitie.setStartDate(dateFrom != null ? java.time.LocalDateTime.parse(dateFrom + "T00:00:00") : null);
        searchFormalitie.setEndDate(dateTo != null ? java.time.LocalDateTime.parse(dateTo + "T23:59:59") : null);

        PageResult<Formalitie> formalities = formalitieService.findFormalities(searchFormalitie, page, size);


        if (formalities == null || formalities.content().isEmpty()) {
            return ResponseEntity.ok().body(
                    new ApiResponse<>(false, "No formalities found", null)
            );
        }

        return ResponseEntity.ok().body(
                new ApiResponse<>(true, "Formalities retrieved successfully", formalities)
        );


    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Formalitie>> findById(@PathVariable int id
    ) {
        Formalitie formalitie = formalitieService.findFormalitieById(id);
        if (formalitie != null) {
            return ResponseEntity.ok(
                    new ApiResponse<Formalitie>(true, "Formalitie found", formalitie)
            );
        } else {
            return ResponseEntity.ok(
                    new ApiResponse<Formalitie>(false, "Formalitie not found", null
            ));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Formalitie>> createFormalitie(@RequestBody FormalitieRequest formalitie) {
        Formalitie createdFormalitie = formalitieService.createFormalitie(formalitie);
        return ResponseEntity.ok(
                new ApiResponse<Formalitie>(true, "Formalitie created successfully", createdFormalitie)
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Void>> changeFormalitieState(@RequestParam int formalitieId,
                                                                   @RequestParam int newState) {
        formalitieService.changeFormalitieState(formalitieId, newState);
        return ResponseEntity.ok(
                new ApiResponse<Void>(true, "Formalitie state changed successfully", null)
        );
    }




}
