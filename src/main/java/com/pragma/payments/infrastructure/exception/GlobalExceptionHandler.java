package com.pragma.payments.infrastructure.exception;

import com.pragma.payments.infrastructure.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String DEFAULT_ERROR_MESSAGE = "Ha ocurrido un error inesperado";

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ErrorResponse> handleTransactionException(
            TransactionException ex, WebRequest request) {
        
        logger.warn("Error de transacción: código={}, mensaje={}, transacción={}",
                ex.getErrorCode(), ex.getMessage(), ex.getTransactionId());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(ex.getHttpStatus().value())
                .error(ex.getHttpStatus().getReasonPhrase())
                .code(ex.getErrorCode())
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .transactionId(ex.getTransactionId())
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Valor inválido",
                        (existing, replacement) -> existing
                ));

        String detailedMessage = fieldErrors.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.joining(", "));

        logger.warn("Error de validación: {}", detailedMessage);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("VALIDATION-001")
                .message("Error de validación en los datos proporcionados")
                .details(fieldErrors)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        
        Map<String, String> violations = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        violation -> violation.getMessage(),
                        (existing, replacement) -> existing
                ));

        logger.warn("Violación de constrain: {}", violations);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("VALIDATION-002")
                .message("Violación de restricciones de validación")
                .details(violations)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        String message = String.format("El parámetro '%s' esperaba un valor de tipo %s pero recibió '%s'",
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido",
                ex.getValue());

        logger.warn("Tipo de argumento incorrecto: {}", message);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("TYPE_MISMATCH-001")
                .message(message)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleUnreadableMessage(
            HttpMessageNotReadableException ex, WebRequest request) {
        
        logger.warn("Mensaje no legible: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("PARSE-001")
                .message("El cuerpo de la solicitud no puede ser procesado. Verifique el formato JSON")
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        logger.warn("Argumento ilegal: {}", ex.getMessage());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .code("ARGUMENT-001")
                .message(ex.getMessage() != null ? ex.getMessage() : "Argumento inválido")
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(
            Exception ex, WebRequest request) {
        
        logger.error("Error no manejado: mensaje={}, tipo={}, traza={}",
                ex.getMessage(),
                ex.getClass().getSimpleName(),
                ex.getStackTrace()[0].toString(),
                ex);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .code("INTERNAL-001")
                .message(DEFAULT_ERROR_MESSAGE)
                .path(request.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}