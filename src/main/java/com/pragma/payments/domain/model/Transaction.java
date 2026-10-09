package com.pragma.payments.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record Transaction(
    @NotNull(message = "Transaction ID cannot be null")
    UUID id,

    @NotNull(message = "Account ID cannot be null")
    UUID accountId,

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    BigDecimal amount,

    @NotNull(message = "Currency cannot be null")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a 3-letter ISO code")
    String currency,

    @NotNull(message = "Transaction date cannot be null")
    LocalDateTime transactionDate,

    @NotNull(message = "Status cannot be null")
    TransactionStatus status,

    @NotBlank(message = "Merchant ID cannot be blank")
    String merchantId,

    @NotBlank(message = "Reference cannot be blank")
    String reference,

    @NotNull(message = "Fraud check status cannot be null")
    FraudCheckStatus fraudCheckStatus,

    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public enum TransactionStatus {
        PENDING, COMPLETED, FAILED, REVERSED
    }

    public enum FraudCheckStatus {
        NOT_CHECKED, PENDING, APPROVED, REJECTED
    }

    public Transaction {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }

    public Transaction withStatus(TransactionStatus newStatus) {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.currency,
            this.transactionDate,
            newStatus,
            this.merchantId,
            this.reference,
            this.fraudCheckStatus,
            this.createdAt,
            LocalDateTime.now()
        );
    }

    public Transaction withFraudCheckStatus(FraudCheckStatus newStatus) {
        return new Transaction(
            this.id,
            this.accountId,
            this.amount,
            this.currency,
            this.transactionDate,
            this.status,
            this.merchantId,
            this.reference,
            newStatus,
            this.createdAt,
            LocalDateTime.now()
        );
    }
}