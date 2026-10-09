package com.pragma.payments.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;

@Schema(description = "Respuesta de error estandarizada para todos los endpoints de la API")
public record ErrorResponse(
    @Schema(description = "Código de estado HTTP de la respuesta", example = "400")
    int status,
    
    @Schema(description = "Título breve del error", example = "Bad Request")
    String error,
    
    @Schema(description = "Mensaje descriptivo del error", example = "El monto de la transacción debe ser mayor a cero")
    String message,
    
    @Schema(description = "Timestamp exacto cuando ocurrió el error en formato ISO-8601", example = "2024-01-15T10:30:00.123Z")
    Instant timestamp,
    
    @Schema(description = "Ruta del endpoint que generó el error", example = "/api/v1/transactions")
    String path,
    
    @Schema(description = "Método HTTP utilizado en la petición", example = "POST")
    String method
) {
    public ErrorResponse {
        if (timestamp == null) {
            timestamp = Instant.now();
        }
    }

    public static ErrorResponse of(int status, String error, String message, String path, String method) {
        return new ErrorResponse(status, error, message, Instant.now(), path, method);
    }

    public static ErrorResponse badRequest(String message, String path, String method) {
        return of(400, "Bad Request", message, path, method);
    }

    public static ErrorResponse notFound(String message, String path, String method) {
        return of(404, "Not Found", message, path, method);
    }

    public static ErrorResponse internalError(String message, String path, String method) {
        return of(500, "Internal Server Error", message, path, method);
    }

    public static ErrorResponse serviceUnavailable(String message, String path, String method) {
        return of(503, "Service Unavailable", message, path, method);
    }

    public static ErrorResponse tooManyRequests(String message, String path, String method) {
        return of(429, "Too Many Requests", message, path, method);
    }
}