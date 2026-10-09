package com.pragma.payments.domain.port;



import com.pragma.payments.domain.model.FraudCheckStatus;
import com.pragma.payments.domain.model.TransactionStatus;
import com.pragma.payments.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    
    Optional<Transaction> findById(UUID id);
    
    List<Transaction> findByAccountId(UUID accountId);
    
    List<Transaction> findByStatus(Transaction.TransactionStatus status);
    
    List<Transaction> findByFraudCheckStatus(Transaction.FraudCheckStatus status);
    
    List<Transaction> findByMerchantId(String merchantId);
}