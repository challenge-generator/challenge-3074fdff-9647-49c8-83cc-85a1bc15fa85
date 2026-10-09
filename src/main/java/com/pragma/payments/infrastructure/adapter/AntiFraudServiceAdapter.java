package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.port.AntiFraudService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
public class AntiFraudServiceAdapter implements AntiFraudService {

    private static final Logger log = LoggerFactory.getLogger(AntiFraudServiceAdapter.class);
    private static final BigDecimal HIGH_RISK_THRESHOLD = BigDecimal.valueOf(5000.00);
    private static final BigDecimal MEDIUM_RISK_THRESHOLD = BigDecimal.valueOf(1000.00);
    private static final int MAX_DAILY_TRANSACTIONS = 50;
    private static final Random random = new Random();

    @Value("${app.antifraud.endpoint:http://localhost:8083/api/antifraud}")
    private String antiFraudEndpoint;

    @Value("${app.antifraud.timeout-ms:5000}")
    private int timeoutMs;

    @Override
    @CircuitBreaker(name = "antiFraudCircuitBreaker", fallbackMethod = "fallbackEvaluateTransaction")
    @Retry(name = "antiFraudRetry")
    public CompletionStage<FraudCheckStatus> evaluateTransaction(Transaction transaction) {
        log.info("Evaluando transacción {} en servicio antifraude", transaction.id());
        log.debug("Endpoint de antifraude: {}, timeout: {}ms", antiFraudEndpoint, timeoutMs);
        
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(random.nextInt(500) + 100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            FraudCheckStatus status = performRiskAnalysis(transaction);
            return status;
        });
    }

    private FraudCheckStatus performRiskAnalysis(Transaction transaction) {
        log.debug("Analizando riesgo para transacción: {}, monto: {}", 
            transaction.id(), transaction.amount());
        
        if (transaction.amount().compareTo(HIGH_RISK_THRESHOLD) > 0) {
            log.warn("Transacción {} flaggeada por monto alto: {}", 
                transaction.id(), transaction.amount());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (isUnusualPattern(transaction)) {
            log.warn("Transacción {} flaggeada por patrón inusual", transaction.id());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (isHighFrequencyTransaction(transaction)) {
            log.warn("Transacción {} flaggeada por alta frecuencia", transaction.id());
            return FraudCheckStatus.FLAGGED;
        }
        
        if (transaction.amount().compareTo(MEDIUM_RISK_THRESHOLD) > 0) {
            log.info("Transacción {} requiere revisión manual", transaction.id());
            return FraudCheckStatus.REVIEW;
        }
        
        log.info("Transacción {} aprobada por antifraude", transaction.id());
        return FraudCheckStatus.APPROVED;
    }

    private boolean isUnusualPattern(Transaction transaction) {
        String merchantId = transaction.merchantId();
        if (merchantId != null && merchantId.contains("TEST")) {
            return true;
        }
        
        String description = transaction.description();
        if (description != null && (description.toLowerCase().contains("sospechoso") || 
                                    description.toLowerCase().contains("fraude"))) {
            return true;
        }
        
        return false;
    }

    private boolean isHighFrequencyTransaction(Transaction transaction) {
        if (transaction.amount().compareTo(BigDecimal.valueOf(100)) > 0) {
            int hourOfDay = java.time.LocalDateTime.now().getHour();
            if (hourOfDay >= 2 && hourOfDay <= 5) {
                log.debug("Transacción {} en horario de alto riesgo ({}:00)", 
                    transaction.id(), hourOfDay);
                return true;
            }
        }
        return false;
    }

    private CompletionStage<FraudCheckStatus> fallbackEvaluateTransaction(Transaction transaction, Throwable ex) {
        log.error("Fallback activado para evaluación antifraude de {}. Error: {}", 
            transaction.id(), ex.getMessage());
        
        if (transaction.amount().compareTo(HIGH_RISK_THRESHOLD) > 0) {
            return CompletableFuture.completedFuture(FraudCheckStatus.FLAGGED);
        }
        
        return CompletableFuture.completedFuture(FraudCheckStatus.REVIEW);
    }
}