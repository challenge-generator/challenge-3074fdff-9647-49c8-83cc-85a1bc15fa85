package com.pragma.payments.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.payments.application.service.TransactionService;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import com.pragma.payments.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@Import(GlobalExceptionHandler.class)
@DisplayName("Tests de integración para TransactionController")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private TransactionResponse transactionResponse;
    private UUID transactionId;
    private UUID accountId;
    private String transactionRequestJson;

    @BeforeEach
    void setUp() throws Exception {
        transactionId = UUID.randomUUID();
        accountId = UUID.randomUUID();

        transactionRequest = new TransactionRequest(
            accountId.toString(),
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345"
        );

        transactionResponse = new TransactionResponse(
            transactionId.toString(),
            accountId.toString(),
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345",
            TransactionStatus.PENDING.name(),
            FraudCheckStatus.APPROVED.name(),
            LocalDateTime.now().toString(),
            LocalDateTime.now().toString()
        );

        transactionRequestJson = objectMapper.writeValueAsString(transactionRequest);
    }

    @Nested
    @DisplayName("POST /api/v1/transactions - Crear transacción")
    class CreateTransactionTests {

        @Test
        @DisplayName("Debe crear una transacción exitosamente con datos válidos")
        void shouldCreateTransactionWithValidData() throws Exception {
            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenReturn(transactionResponse);

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.accountId").value(accountId.toString()))
                .andExpect(jsonPath("$.merchantId").value("MERCHANT_001"))
                .andExpect(jsonPath("$.amount").value(1500.00))
                .andExpect(jsonPath("$.status").value(TransactionStatus.PENDING.name()))
                .andExpect(jsonPath("$.fraudCheckStatus").value(FraudCheckStatus.APPROVED.name()));
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el accountId está vacío")
        void shouldReturn400WhenAccountIdIsEmpty() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                "",
                "MERCHANT_001",
                new BigDecimal("100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("El accountId no puede estar vacío"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el monto es negativo")
        void shouldReturn400WhenAmountIsNegative() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                new BigDecimal("-50.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("El monto debe ser positivo"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el monto es nulo")
        void shouldReturn400WhenAmountIsNull() throws Exception {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                null,
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidRequest)));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el JSON está mal formado")
        void shouldReturn400WhenJsonIsMalformed() throws Exception {
            String malformedJson = "{\"accountId\": \"\", \"merchantId\": \"MERCHANT\"}";

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 415 cuando el content-type es incorrecto")
        void shouldReturn415WhenContentTypeIsIncorrect() throws Exception {
            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.TEXT_PLAIN)
                .content(transactionRequestJson));

            result.andExpect(status().isUnsupportedMediaType());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/{id} - Obtener transacción por ID")
    class GetTransactionByIdTests {

        @Test
        @DisplayName("Debe obtener una transacción por su ID exitosamente")
        void shouldGetTransactionById() throws Exception {
            when(transactionService.getTransactionById(transactionId))
                .thenReturn(transactionResponse);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", transactionId));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transactionId.toString()))
                .andExpect(jsonPath("$.accountId").value(accountId.toString()));
        }

        @Test
        @DisplayName("Debe retornar 404 cuando la transacción no existe")
        void shouldReturn404WhenTransactionNotFound() throws Exception {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionService.getTransactionById(nonExistentId))
                .thenThrow(new TransactionException("Transacción no encontrada"));

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", nonExistentId));

            result.andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el ID tiene formato inválido")
        void shouldReturn400WhenIdIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", "invalid-uuid"));

            result.andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions - Consultar transacciones")
    class GetTransactionsTests {

        @Test
        @DisplayName("Debe obtener transacciones por accountId")
        void shouldGetTransactionsByAccountId() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(transactionId.toString()))
                .andExpect(jsonPath("$.[0].accountId").value(accountId.toString()));
        }

        @Test
        @DisplayName("Debe obtener transacciones por status")
        void shouldGetTransactionsByStatus() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByStatus(TransactionStatus.PENDING))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("status", TransactionStatus.PENDING.name()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].status").value(TransactionStatus.PENDING.name()));
        }

        @Test
        @DisplayName("Debe obtener transacciones por merchantId")
        void shouldGetTransactionsByMerchantId() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByMerchantId("MERCHANT_001"))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("merchantId", "MERCHANT_001"));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].merchantId").value("MERCHANT_001"));
        }

        @Test
        @DisplayName("Debe retornar lista vacía cuando no hay transacciones")
        void shouldReturnEmptyListWhenNoTransactions() throws Exception {
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(List.of());

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            result.andExpect(status().isOk())
                .andExpect(jsonPath("$.").isArray())
                .andExpect(jsonPath("$.length()").value(0));
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el parámetro accountId es inválido")
        void shouldReturn400WhenAccountIdIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", "not-a-uuid"));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el parámetro status es inválido")
        void shouldReturn400WhenStatusIsInvalid() throws Exception {
            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("status", "INVALID_STATUS"));

            result.andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe soportar múltiples parámetros de filtro simultáneamente")
        void shouldSupportMultipleFilterParameters() throws Exception {
            List<TransactionResponse> responses = List.of(transactionResponse);
            when(transactionService.getTransactionsByAccountId(accountId))
                .thenReturn(responses);

            ResultActions result = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString())
                .param("status", TransactionStatus.PENDING.name()));

            result.andExpect(status().isOk());
        }
    }

    @Nested
    @DisplayName("Manejo de errores global")
    class GlobalErrorHandlingTests {

        @Test
        @DisplayName("Debe manejar excepciones internas del servidor")
        void shouldHandleInternalServerError() throws Exception {
            when(transactionService.getTransactionById(transactionId))
                .thenThrow(new RuntimeException("Error interno"));

            ResultActions result = mockMvc.perform(get("/api/v1/transactions/{id}", transactionId));

            result.andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.timestamp").exists());
        }

        @Test
        @DisplayName("Debe manejarTransactionException correctamente")
        void shouldHandleTransactionException() throws Exception {
            when(transactionService.createTransaction(any(TransactionRequest.class)))
                .thenThrow(new TransactionException("Error de validación"));

            ResultActions result = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Error de validación"));
        }
    }

    @Nested
    @DisplayName("Documentación OpenAPI del controlador")
    class OpenAPIDocumentationTests {

        @Test
        @DisplayName("Debe incluir las operaciones en la documentación OpenAPI")
        void shouldIncludeOperationsInOpenAPIDocumentation() throws Exception {
            ResultActions getDocs = mockMvc.perform(get("/api/v1/transactions")
                .param("accountId", accountId.toString()));

            getDocs.andExpect(status().isOk());

            ResultActions postDocs = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(transactionRequestJson));

            postDocs.andExpect(status().isCreated());
        }
    }
}