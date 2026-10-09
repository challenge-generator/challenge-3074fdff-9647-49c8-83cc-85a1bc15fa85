package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import com.pragma.payments.domain.port.AntiFraudService;
import com.pragma.payments.infrastructure.dto.TransactionRequest;
import com.pragma.payments.infrastructure.dto.TransactionResponse;
import com.pragma.payments.infrastructure.exception.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para TransactionService")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AntiFraudService antiFraudService;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionRequest transactionRequest;
    private Transaction transaction;
    private UUID transactionId;
    private UUID accountId;

    @BeforeEach
    void setUp() {
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

        transaction = new Transaction(
            transactionId,
            accountId,
            "MERCHANT_001",
            new BigDecimal("1500.00"),
            "USD",
            "PURCHASE",
            "Orden de compra #12345",
            TransactionStatus.PENDING,
            FraudCheckStatus.PENDING,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    @Nested
    @DisplayName("Creación de transacciones")
    class CreateTransactionTests {

        @Test
        @DisplayName("Debe crear una transacción exitosamente cuando el antifraude aprueba")
        void shouldCreateTransactionSuccessfullyWhenAntiFraudApproves() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.APPROVED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.accountId()).isEqualTo(accountId.toString());
            assertThat(response.status()).isEqualTo(TransactionStatus.PENDING.name());
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.APPROVED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar transacción cuando el antifraude la marca como sospechosa")
        void shouldRejectTransactionWhenAntiFraudMarksAsSuspect() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.SUSPECTED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.SUSPECTED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar transacción cuando el antifraude la marca como fraudulenta")
        void shouldRejectTransactionWhenAntiFraudMarksAsFraudulent() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.completedFuture(FraudCheckStatus.REJECTED));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.REJECTED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el monto es nulo")
        void shouldThrowExceptionWhenAmountIsNull() {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                null,
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("monto");
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el monto es negativo")
        void shouldThrowExceptionWhenAmountIsNegative() {
            TransactionRequest invalidRequest = new TransactionRequest(
                accountId.toString(),
                "MERCHANT_001",
                new BigDecimal("-100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("positivo");
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el accountId es vacío")
        void shouldThrowExceptionWhenAccountIdIsEmpty() {
            TransactionRequest invalidRequest = new TransactionRequest(
                "",
                "MERCHANT_001",
                new BigDecimal("100.00"),
                "USD",
                "PURCHASE",
                "Orden de compra"
            );

            assertThatThrownBy(() -> transactionService.createTransaction(invalidRequest))
                .isInstanceOf(TransactionException.class);
            verify(transactionRepository, never()).save(any(Transaction.class));
        }
    }

    @Nested
    @DisplayName("Consulta de transacciones")
    class QueryTransactionTests {

        @Test
        @DisplayName("Debe obtener una transacción por su ID")
        void shouldGetTransactionById() {
            when(transactionRepository.findById(transactionId))
                .thenReturn(Optional.of(transaction));

            TransactionResponse response = transactionService.getTransactionById(transactionId);

            assertThat(response).isNotNull();
            assertThat(response.id()).isEqualTo(transactionId.toString());
            assertThat(response.accountId()).isEqualTo(accountId.toString());
            verify(transactionRepository, times(1)).findById(transactionId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando la transacción no existe")
        void shouldThrowExceptionWhenTransactionNotFound() {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionRepository.findById(nonExistentId))
                .thenReturn(Optional.empty());

            assertThatThrownBy(() -> transactionService.getTransactionById(nonExistentId))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("no encontrada");
        }

        @Test
        @DisplayName("Debe obtener transacciones por accountId")
        void shouldGetTransactionsByAccountId() {
            List<Transaction> transactions = List.of(transaction);
            when(transactionRepository.findByAccountId(accountId))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByAccountId(accountId);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).accountId()).isEqualTo(accountId.toString());
            verify(transactionRepository, times(1)).findByAccountId(accountId);
        }

        @Test
        @DisplayName("Debe obtener transacciones por status")
        void shouldGetTransactionsByStatus() {
            List<Transaction> transactions = List.of(transaction);
            when(transactionRepository.findByStatus(TransactionStatus.PENDING))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByStatus(TransactionStatus.PENDING);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).status()).isEqualTo(TransactionStatus.PENDING.name());
            verify(transactionRepository, times(1)).findByStatus(TransactionStatus.PENDING);
        }

        @Test
        @DisplayName("Debe obtener transacciones por merchantId")
        void shouldGetTransactionsByMerchantId() {
            List<Transaction> transactions = List.of(transaction);
            String merchantId = "MERCHANT_001";
            when(transactionRepository.findByMerchantId(merchantId))
                .thenReturn(transactions);

            List<TransactionResponse> responses = transactionService.getTransactionsByMerchantId(merchantId);

            assertThat(responses).hasSize(1);
            assertThat(responses.get(0).merchantId()).isEqualTo(merchantId);
            verify(transactionRepository, times(1)).findByMerchantId(merchantId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el accountId es inválido")
        void shouldThrowExceptionWhenAccountIdIsInvalid() {
            assertThatThrownBy(() -> transactionService.getTransactionsByAccountId(null))
                .isInstanceOf(TransactionException.class);
        }
    }

    @Nested
    @DisplayName("Actualización de transacciones")
    class UpdateTransactionTests {

        @Test
        @DisplayName("Debe actualizar el status de una transacción exitosamente")
        void shouldUpdateTransactionStatus() {
            Transaction updatedTransaction = transaction.withStatus(TransactionStatus.COMPLETED);
            when(transactionRepository.findById(transactionId))
                .thenReturn(Optional.of(transaction));
            when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(updatedTransaction);

            TransactionResponse response = transactionService.updateTransactionStatus(
                transactionId, TransactionStatus.COMPLETED);

            assertThat(response).isNotNull();
            assertThat(response.status()).isEqualTo(TransactionStatus.COMPLETED.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción al actualizar una transacción inexistente")
        void shouldThrowExceptionWhenUpdatingNonExistentTransaction() {
            UUID nonExistentId = UUID.randomUUID();
            when(transactionRepository.findById(nonExistentId))
                .thenReturn(Optional.empty());

            assertThatThrownBy(() -> transactionService.updateTransactionStatus(
                nonExistentId, TransactionStatus.COMPLETED))
                .isInstanceOf(TransactionException.class);
            verify(transactionRepository, never()).save(any(Transaction.class));
        }
    }

    @Nested
    @DisplayName("Integración con AntiFraudService")
    class AntiFraudIntegrationTests {

        @Test
        @DisplayName("Debe manejar falla en el servicio antifraude gracefully")
        void shouldHandleAntiFraudServiceFailure() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.failedFuture(new RuntimeException("Servicio no disponible")));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            TransactionResponse response = transactionService.createTransaction(transactionRequest);

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.PENDING.name());
            verify(transactionRepository, times(1)).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe esperar el resultado del antifraude de forma asíncrona")
        void shouldWaitForAsyncAntiFraudResult() {
            when(antiFraudService.evaluateTransaction(any(Transaction.class)))
                .thenReturn(CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return FraudCheckStatus.APPROVED;
                }));
            when(transactionRepository.save(any(Transaction.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

            long startTime = System.currentTimeMillis();
            TransactionResponse response = transactionService.createTransaction(transactionRequest);
            long endTime = System.currentTimeMillis();

            assertThat(response).isNotNull();
            assertThat(response.fraudCheckStatus()).isEqualTo(FraudCheckStatus.APPROVED.name());
            assertThat(endTime - startTime).isGreaterThanOrEqualTo(50);
        }
    }
}