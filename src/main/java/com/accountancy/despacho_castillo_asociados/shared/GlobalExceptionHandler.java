package com.accountancy.despacho_castillo_asociados.shared;

import com.accountancy.despacho_castillo_asociados.shared.exceptions.BadRequestException;
import com.accountancy.despacho_castillo_asociados.shared.exceptions.EmptyListException;
import com.accountancy.despacho_castillo_asociados.shared.utils.ExtractColumn;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

import static com.accountancy.despacho_castillo_asociados.shared.utils.ExtractColumn.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Messages messages;

    public GlobalExceptionHandler(Messages messages) {
        this.messages = messages;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(EntityNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ApiResponse<ArrayList<Object>>> handleEmptyList(EmptyListException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, ex.getMessage(), new ArrayList<>()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrity(DataIntegrityViolationException ex) {

        Throwable root = ex.getRootCause();

        if (root instanceof SQLException sqlEx) {

            String message = sqlEx.getMessage();

            switch (sqlEx.getErrorCode()) {

                case 1062: { // Duplicate
                    String column = extractDuplicateColumn(message);
                    String value = extractDuplicateValue(message);

                    String friendlyColumn = messages.getFriendlyName(column, Locale.getDefault());

                    return buildResponse(
                            messages.get("database.duplicate.entry",
                                    new Object[]{friendlyColumn, value})
                    );
                }

                case 1048: { // Not null
                    String column = extractNotNullColumn(message);

                    String friendlyColumn = messages.getFriendlyName(column, Locale.getDefault());

                    return buildResponse(
                            messages.get("database.not.null.violation",
                                    new Object[]{friendlyColumn})
                    );
                }

                case 1406: { // Data too long
                    String column = extractDataTooLongColumn(message);

                    String friendlyColumn = messages.getFriendlyName(column, Locale.getDefault());

                    return buildResponse(
                            messages.get("database.data.too.long",
                                    new Object[]{friendlyColumn})
                    );
                }

                case 1452: { // FK
                    return buildResponse(
                            messages.get("database.foreign.key.violation")
                    );
                }
            }
        }

        return buildResponse(messages.get("database.integrity.generic"));
    }

    private ResponseEntity<?> buildResponse(String message) {
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(false, message, null)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, ex.getMessage(), null));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ApiResponse<Void>> handleIOException(IOException ex) {

        System.out.println("IOException: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse<>(false, messages.get("file.io.exception"), null));
    }
}
