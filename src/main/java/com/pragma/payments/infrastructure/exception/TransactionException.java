package com.pragma.payments.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class TransactionException extends RuntimeException {

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String transactionId;

    public enum ErrorCode {
        DUPLICATE_TRANSACTION("PAGO-001", HttpStatus.CONFLICT, "Transacción duplicada"),
        INSUFFICIENT_FUNDS("PAGO-002", HttpStatus.PAYMENT_REQUIRED, "Fondos insuficientes"),
        INVALID_AMOUNT("PAGO-003", HttpStatus.BAD_REQUEST, "Monto inválido para la transacción"),
        ACCOUNT_NOT_FOUND("PAGO-004", HttpStatus.NOT_FOUND, "Cuenta no encontrada"),
        MERCHANT_NOT_FOUND("PAGO-005", HttpStatus.NOT_FOUND, "Comercio no encontrado"),
        FRAUD_DETECTED("PAGO-006", HttpStatus.FORBIDDEN, "Transacción bloqueada por sospecha de fraude"),
        FRAUD_CHECK_TIMEOUT("PAGO-007", HttpStatus.SERVICE_UNAVAILABLE, "Tiempo de espera agotado en verificación antifraude"),
        EXTERNAL_SERVICE_ERROR("PAGO-008", HttpStatus.BAD_GATEWAY, "Error en servicio externo"),
        INVALID_CURRENCY("PAGO-009", HttpStatus.BAD_REQUEST, "Moneda no soportada"),
        TRANSACTION_EXPIRED("PAGO-010", HttpStatus.GONE, "Transacción expirada"),
        TRANSACTION_NOT_FOUND("PAGO-011", HttpStatus.NOT_FOUND, "Transacción no encontrada"),
        INVALID_STATE_TRANSITION("PAGO-012", HttpStatus.BAD_REQUEST, "Transición de estado inválida"),
        AUTHORIZATION_FAILED("PAGO-013", HttpStatus.UNAUTHORIZED, "Autorización denegada"),
        RATE_LIMIT_EXCEEDED("PAGO-014", HttpStatus.TOO_MANY_REQUESTS, "Límite de solicitudes excedido"),
        INTERNAL_PROCESSING_ERROR("PAGO-015", HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en procesamiento");

        private final String code;
        private final HttpStatus httpStatus;
        private final String defaultMessage;

        ErrorCode(String code, HttpStatus httpStatus, String defaultMessage) {
            this.code = code;
            this.httpStatus = httpStatus;
            this.defaultMessage = defaultMessage;
        }

        public String getCode() {
            return code;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

    public TransactionException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = null;
    }

    public TransactionException(ErrorCode errorCode, String message, String transactionId) {
        super(message);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = transactionId;
    }

    public TransactionException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = null;
    }

    public TransactionException(ErrorCode errorCode, String message, String transactionId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode.getCode();
        this.httpStatus = errorCode.getHttpStatus();
        this.transactionId = transactionId;
    }

    public static TransactionException duplicateTransaction(String transactionId) {
        return new TransactionException(
            ErrorCode.DUPLICATE_TRANSACTION,
            "Ya existe una transacción con el identificador proporcionado",
            transactionId
        );
    }

    public static TransactionException insufficientFunds(String transactionId, String accountId) {
        return new TransactionException(
            ErrorCode.INSUFFICIENT_FUNDS,
            "La cuenta " + accountId + " no tiene fondos suficientes para completar la transacción",
            transactionId
        );
    }

    public static TransactionException fraudDetected(String transactionId, String reason) {
        return new TransactionException(
            ErrorCode.FRAUD_DETECTED,
            "Transacción bloqueada: " + reason,
            transactionId
        );
    }

    public static TransactionException transactionNotFound(UUID transactionId) {
        return new TransactionException(
            ErrorCode.TRANSACTION_NOT_FOUND,
            "No se encontró la transacción con ID: " + transactionId,
            transactionId.toString()
        );
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }
}