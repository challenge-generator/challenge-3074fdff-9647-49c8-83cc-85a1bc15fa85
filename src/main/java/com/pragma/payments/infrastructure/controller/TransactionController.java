package com.pragma.payments.infrastructure.controller;



import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.application.service.TransactionService;
import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.infrastructure.dto.ErrorResponse;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transacciones", description = "Operaciones CRUD para gestión de transacciones de pago")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @Operation(summary = "Crear transacción", description = "Registra una nueva transacción de pago en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request) {
        Transaction transaction = transactionService.createTransaction(request.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TransactionResponse.fromDomain(transaction));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Recupera los detalles de una transacción específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transacción encontrada",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> getTransactionById(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id) {
        Transaction transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(TransactionResponse.fromDomain(transaction));
    }

    @GetMapping
    @Operation(summary = "Listar transacciones", description = "Obtiene todas las transacciones con filtros opcionales")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de transacciones",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class)))
    })
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @Parameter(description = "Filtrar por ID de cuenta")
            @RequestParam(required = false) UUID accountId,
            @Parameter(description = "Filtrar por estado de transacción")
            @RequestParam(required = false) Transaction.TransactionStatus status,
            @Parameter(description = "Filtrar por estado de verificación antifraude")
            @RequestParam(required = false) Transaction.FraudCheckStatus fraudStatus,
            @Parameter(description = "Filtrar por ID de comerciante")
            @RequestParam(required = false) String merchantId) {
        List<Transaction> transactions = transactionService.getTransactionsByFilters(
                accountId, status, fraudStatus, merchantId);
        List<TransactionResponse> responses = transactions.stream()
                .map(TransactionResponse::fromDomain)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de transacción", description = "Modifica el estado de una transacción existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente",
                    content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Estado inválido para la transición",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransactionResponse> updateTransactionStatus(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id,
            @Parameter(description = "Nuevo estado de la transacción", required = true)
            @RequestParam Transaction.TransactionStatus status) {
        Transaction transaction = transactionService.updateStatus(id, status);
        return ResponseEntity.ok(TransactionResponse.fromDomain(transaction));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar transacción", description = "Elimina una transacción del sistema (soft delete)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transacción eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Transacción no encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteTransaction(
            @Parameter(description = "UUID de la transacción", required = true)
            @PathVariable UUID id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(TransactionException ex) {
        ErrorResponse error = new ErrorResponse(
                ex.getHttpStatus().value(),
                ex.getMessage(),
                "/api/v1/transactions",
                "POST");
        return ResponseEntity.status(ex.getHttpStatus()).body(error);
    }
}