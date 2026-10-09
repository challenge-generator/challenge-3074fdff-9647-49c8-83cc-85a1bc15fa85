package com.pragma.payments.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Respuesta de transacción de pago con toda la información del procesamiento")
public record TransactionResponse(

    @JsonProperty("id")
    @Schema(description = "Identificador único de la transacción", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id,

    @JsonProperty("account_id")
    @Schema(description = "Identificador de la cuenta asociada", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID accountId,

    @Schema(description = "Monto de la transacción", example = "150.75")
    BigDecimal amount,

    @Schema(description = "Código de moneda ISO 4217", example = "USD")
    String currency,

    @Schema(description = "Tipo de transacción", example = "PAYMENT")
    String type,

    @Schema(description = "Estado actual de la transacción", example = "PROCESSED")
    String status,

    @JsonProperty("fraud_check_status")
    @Schema(description = "Resultado de la verificación antifraude", example = "APPROVED")
    String fraudCheckStatus,

    @JsonProperty("merchant_id")
    @Schema(description = "Identificador del comercio", example = "MERCH-001")
    String merchantId,

    @Schema(description = "Descripción de la transacción", example = "Pago de servicio")
    String description,

    @JsonProperty("reference_number")
    @Schema(description = "Número de referencia único", example = "TXN-2024-001234")
    String referenceNumber,

    @JsonProperty("created_at")
    @Schema(description = "Fecha de creación de la transacción", example = "2024-01-15T10:30:00Z")
    Instant createdAt,

    @JsonProperty("updated_at")
    @Schema(description = "Última fecha de actualización", example = "2024-01-15T10:30:05Z")
    Instant updatedAt,

    @JsonProperty("processed_at")
    @Schema(description = "Fecha de procesamiento de la transacción", example = "2024-01-15T10:30:03Z")
    Instant processedAt,

    @JsonProperty("error_code")
    @Schema(description = "Código de error si la transacción falló", example = "INSUFFICIENT_FUNDS")
    String errorCode,

    @JsonProperty("error_message")
    @Schema(description = "Mensaje de error detallado", example = "Fondos insuficientes en la cuenta")
    String errorMessage
) {
    public static TransactionResponse fromDomain(Transaction transaction) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.currency(),
            transaction.type(),
            transaction.status().name(),
            transaction.fraudCheckStatus().name(),
            transaction.merchantId(),
            transaction.description(),
            transaction.referenceNumber(),
            transaction.createdAt(),
            transaction.updatedAt(),
            null,
            null,
            null
        );
    }

    public static TransactionResponse fromDomainWithProcessingTime(Transaction transaction, Instant processedAt) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.currency(),
            transaction.type(),
            transaction.status().name(),
            transaction.fraudCheckStatus().name(),
            transaction.merchantId(),
            transaction.description(),
            transaction.referenceNumber(),
            transaction.createdAt(),
            transaction.updatedAt(),
            processedAt,
            null,
            null
        );
    }

    public static TransactionResponse error(UUID transactionId, String errorCode, String errorMessage) {
        return new TransactionResponse(
            transactionId,
            null,
            null,
            null,
            null,
            TransactionStatus.FAILED.name(),
            FraudCheckStatus.UNKNOWN.name(),
            null,
            null,
            null,
            Instant.now(),
            Instant.now(),
            null,
            errorCode,
            errorMessage
        );
    }

    public boolean isSuccessful() {
        return status != null && status.equals(TransactionStatus.PROCESSED.name());
    }

    public boolean isPending() {
        return status != null && status.equals(TransactionStatus.PENDING.name());
    }

    public boolean isFailed() {
        return status != null && status.equals(TransactionStatus.FAILED.name());
    }

    public boolean hasFraudCheckApproved() {
        return fraudCheckStatus != null && fraudCheckStatus.equals(FraudCheckStatus.APPROVED.name());
    }
}