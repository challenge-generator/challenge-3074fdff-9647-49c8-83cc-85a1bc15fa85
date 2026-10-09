package com.pragma.payments.application.service;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import com.pragma.payments.domain.port.AntiFraudService;
import com.pragma.payments.infrastructure.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal MIN_AMOUNT = BigDecimal.valueOf(0.01);
    private static final BigDecimal MAX_AMOUNT = BigDecimal.valueOf(100000.00);

    private final TransactionRepository transactionRepository;
    private final AntiFraudService antiFraudService;

    public TransactionService(TransactionRepository transactionRepository, AntiFraudService antiFraudService) {
        this.transactionRepository = transactionRepository;
        this.antiFraudService = antiFraudService;
    }

    public Transaction createTransaction(String accountId, BigDecimal amount, String currency, 
                                          String merchantId, String description) {
        log.info("Iniciando creación de transacción para cuenta: {}, monto: {}", accountId, amount);
        
        validateTransactionInput(accountId, amount, currency, merchantId);
        
        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            amount,
            currency,
            merchantId,
            description,
            TransactionStatus.PENDING,
            FraudCheckStatus.PENDING,
            Instant.now(),
            Instant.now()
        );
        
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transacción creada exitosamente con ID: {}", savedTransaction.id());
        
        evaluateFraudAsync(savedTransaction);
        
        return savedTransaction;
    }

    public Transaction processTransaction(UUID transactionId) {
        log.info("Procesando transacción: {}", transactionId);
        
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId, 
                "TRANSACTION_NOT_FOUND", 
                404));
        
        if (transaction.status() == TransactionStatus.COMPLETED) {
            log.warn("Transacción {} ya fue procesada", transactionId);
            return transaction;
        }
        
        if (transaction.fraudCheckStatus() == FraudCheckStatus.FLAGGED) {
            log.warn("Transacción {} marcada como sospechosa por antifraude", transactionId);
            Transaction flaggedTransaction = transaction.withStatus(TransactionStatus.FAILED);
            return transactionRepository.save(flaggedTransaction);
        }
        
        Transaction completedTransaction = transaction.withStatus(TransactionStatus.COMPLETED);
        Transaction saved = transactionRepository.save(completedTransaction);
        
        log.info("Transacción {} procesada exitosamente", transactionId);
        return saved;
    }

    public Transaction getTransaction(UUID transactionId) {
        return transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId,
                "TRANSACTION_NOT_FOUND",
                404));
    }

    public List<Transaction> getTransactionsByAccount(String accountId) {
        log.debug("Consultando transacciones para cuenta: {}", accountId);
        return transactionRepository.findByAccountId(UUID.fromString(accountId));
    }

    public List<Transaction> getTransactionsByMerchant(String merchantId) {
        log.debug("Consultando transacciones para comerciante: {}", merchantId);
        return transactionRepository.findByMerchantId(merchantId);
    }

    public List<Transaction> getTransactionsByStatus(TransactionStatus status) {
        log.debug("Consultando transacciones con estado: {}", status);
        return transactionRepository.findByStatus(status);
    }

    public List<Transaction> getFlaggedTransactions() {
        log.debug("Consultando transacciones marcadas por antifraude");
        return transactionRepository.findByFraudCheckStatus(FraudCheckStatus.FLAGGED);
    }

    public Transaction updateTransactionStatus(UUID transactionId, TransactionStatus newStatus) {
        log.info("Actualizando estado de transacción {} a {}", transactionId, newStatus);
        
        Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new TransactionException(
                "Transacción no encontrada: " + transactionId,
                "TRANSACTION_NOT_FOUND",
                404));
        
        Transaction updatedTransaction = transaction.withStatus(newStatus);
        return transactionRepository.save(updatedTransaction);
    }

    private void validateTransactionInput(String accountId, BigDecimal amount, String currency, String merchantId) {
        if (accountId == null || accountId.isBlank()) {
            throw new TransactionException(
                "El ID de cuenta es obligatorio",
                "INVALID_ACCOUNT_ID",
                400);
        }
        
        if (amount == null || amount.compareTo(MIN_AMOUNT) < 0) {
            throw new TransactionException(
                "El monto debe ser mayor a " + MIN_AMOUNT,
                "INVALID_AMOUNT",
                400);
        }
        
        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new TransactionException(
                "El monto no puede exceder " + MAX_AMOUNT,
                "AMOUNT_EXCEEDS_LIMIT",
                400);
        }
        
        if (currency == null || currency.isBlank()) {
            throw new TransactionException(
                "La moneda es obligatoria",
                "INVALID_CURRENCY",
                400);
        }
        
        if (merchantId == null || merchantId.isBlank()) {
            throw new TransactionException(
                "El ID del comerciante es obligatorio",
                "INVALID_MERCHANT_ID",
                400);
        }
    }

    private void evaluateFraudAsync(Transaction transaction) {
        log.info("Enviando transacción {} a evaluación de antifraude", transaction.id());
        
        CompletionStage<Transaction.FraudCheckStatus> fraudResult = antiFraudService.evaluateTransaction(transaction);
        
        fraudResult.thenAccept(status -> {
            log.info("Resultado de antifraude para {}: {}", transaction.id(), status);
            Transaction updatedTransaction = transaction.withFraudCheckStatus(status);
            transactionRepository.save(updatedTransaction);
            
            if (status == FraudCheckStatus.APPROVED) {
                processTransaction(transaction.id());
            }
        }).exceptionally(ex -> {
            log.error("Error en evaluación de antifraude para {}: {}", transaction.id(), ex.getMessage());
            Transaction failedTransaction = transaction.withFraudCheckStatus(FraudCheckStatus.ERROR);
            transactionRepository.save(failedTransaction);
            return null;
        });
    }
}