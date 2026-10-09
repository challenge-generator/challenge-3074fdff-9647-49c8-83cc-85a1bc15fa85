package com.pragma.payments.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(description = "Solicitud para crear o actualizar una transacción de pago")
public record TransactionRequest(

    @JsonProperty("account_id")
    @NotNull(message = "El ID de cuenta es obligatorio")
    @Schema(description = "Identificador único de la cuenta", example = "550e8400-e29b-41d4-a716-446655440000", requiredMode = Schema.RequiredMode.REQUIRED)
    UUID accountId,

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Schema(description = "Monto de la transacción", example = "150.75", requiredMode = Schema.RequiredMode.REQUIRED)
    BigDecimal amount,

    @NotBlank(message = "La moneda es obligatoria")
    @Size(min = 3, max = 3, message = "La moneda debe tener 3 caracteres")
    @Schema(description = "Código de moneda ISO 4217", example = "USD", requiredMode = Schema.RequiredMode.REQUIRED)
    String currency,

    @NotBlank(message = "El tipo de transacción es obligatorio")
    @Size(max = 50, message = "El tipo no puede exceder 50 caracteres")
    @Schema(description = "Tipo de transacción: PAYMENT, REFUND, CHARGEBACK, SETTLEMENT", example = "PAYMENT", requiredMode = Schema.RequiredMode.REQUIRED)
    String type,

    @NotBlank(message = "El ID del comercio es obligatorio")
    @Size(max = 100, message = "El ID del comercio no puede exceder 100 caracteres")
    @Schema(description = "Identificador del comercio", example = "MERCH-001", requiredMode = Schema.RequiredMode.REQUIRED)
    String merchantId,

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción adicional de la transacción", example = "Pago de servicio de streaming")
    String description,

    @Size(max = 100, message = "El número de referencia no puede exceder 100 caracteres")
    @Schema(description = "Número de referencia único de la transacción", example = "TXN-2024-001234")
    String referenceNumber,

    @JsonProperty("callback_url")
    @Schema(description = "URL de callback para notificaciones asíncronas", example = "https://api.example.com/webhooks/payment")
    String callbackUrl,

    @JsonProperty("metadata")
    @Schema(description = "Metadatos adicionales en formato clave-valor")
    java.util.Map<String, String> metadata
) {
    public TransactionRequest {
        if (currency != null) {
            currency = currency.toUpperCase();
        }
        if (type != null) {
            type = type.toUpperCase();
        }
    }

    public static TransactionRequest withDefaults(TransactionRequest request) {
        return new TransactionRequest(
            request.accountId(),
            request.amount(),
            request.currency() != null ? request.currency().toUpperCase() : "USD",
            request.type() != null ? request.type().toUpperCase() : "PAYMENT",
            request.merchantId(),
            request.description(),
            request.referenceNumber() != null ? request.referenceNumber() : generateReference(),
            request.callbackUrl(),
            request.metadata()
        );
    }

    private static String generateReference() {
        return "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public boolean isValidForProcessing() {
        return accountId != null && amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && currency != null && !currency.isBlank() && type != null && !type.isBlank()
            && merchantId != null && !merchantId.isBlank();
    }
}