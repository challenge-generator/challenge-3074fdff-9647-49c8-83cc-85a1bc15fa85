package com.pragma.payments.domain.port;


import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction;
import java.util.concurrent.CompletionStage;

public interface AntiFraudService {
    /**
     * Evaluates a transaction for potential fraud.
     * @param transaction The transaction to evaluate
     * @return A CompletionStage that will complete with the fraud check status
     */
    CompletionStage<Transaction.FraudCheckStatus> evaluateTransaction(Transaction transaction);
}