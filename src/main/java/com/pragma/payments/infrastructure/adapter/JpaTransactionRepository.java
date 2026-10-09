package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.Transaction;
import com.pragma.payments.domain.model.Transaction.FraudCheckStatus;
import com.pragma.payments.domain.model.Transaction.TransactionStatus;
import com.pragma.payments.domain.port.TransactionRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final TransactionJpaRepository jpaRepository;
    private final EntityManager entityManager;

    public JpaTransactionRepository(TransactionJpaRepository jpaRepository, EntityManager entityManager) {
        this.jpaRepository = jpaRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Transaction save(Transaction transaction) {
        if (transaction.id() == null) {
            TransactionEntity entity = toEntity(transaction);
            TransactionEntity saved = jpaRepository.save(entity);
            return toDomain(saved);
        }
        TransactionEntity existing = jpaRepository.findById(transaction.id())
            .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transaction.id()));
        updateEntity(existing, transaction);
        TransactionEntity saved = jpaRepository.save(existing);
        return toDomain(saved);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        List<TransactionEntity> entities = jpaRepository.findByAccountId(accountId);
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByStatus(TransactionStatus status) {
        List<TransactionEntity> entities = jpaRepository.findByStatus(status.name());
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByFraudCheckStatus(FraudCheckStatus status) {
        List<TransactionEntity> entities = jpaRepository.findByFraudCheckStatus(status.name());
        return entities.stream().map(this::toDomain).toList();
    }

    @Override
    public List<Transaction> findByMerchantId(String merchantId) {
        List<TransactionEntity> entities = jpaRepository.findByMerchantId(merchantId);
        return entities.stream().map(this::toDomain).toList();
    }

    private TransactionEntity toEntity(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(transaction.id() != null ? transaction.id() : UUID.randomUUID());
        entity.setAccountId(transaction.accountId());
        entity.setAmount(transaction.amount());
        entity.setCurrency(transaction.currency());
        entity.setType(transaction.type());
        entity.setStatus(transaction.status().name());
        entity.setFraudCheckStatus(transaction.fraudCheckStatus().name());
        entity.setMerchantId(transaction.merchantId());
        entity.setDescription(transaction.description());
        entity.setReferenceNumber(transaction.referenceNumber());
        entity.setCreatedAt(transaction.createdAt());
        entity.setUpdatedAt(transaction.updatedAt());
        return entity;
    }

    private void updateEntity(TransactionEntity existing, Transaction transaction) {
        existing.setAccountId(transaction.accountId());
        existing.setAmount(transaction.amount());
        existing.setCurrency(transaction.currency());
        existing.setType(transaction.type());
        existing.setStatus(transaction.status().name());
        existing.setFraudCheckStatus(transaction.fraudCheckStatus().name());
        existing.setMerchantId(transaction.merchantId());
        existing.setDescription(transaction.description());
        existing.setReferenceNumber(transaction.referenceNumber());
        existing.setUpdatedAt(java.time.Instant.now());
    }

    private Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
            entity.getId(),
            entity.getAccountId(),
            entity.getAmount(),
            entity.getCurrency(),
            entity.getType(),
            TransactionStatus.valueOf(entity.getStatus()),
            FraudCheckStatus.valueOf(entity.getFraudCheckStatus()),
            entity.getMerchantId(),
            entity.getDescription(),
            entity.getReferenceNumber(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    @Entity
    @Table(name = "transactions")
    public static class TransactionEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(name = "account_id", nullable = false)
        private UUID accountId;

        @Column(nullable = false, precision = 19, scale = 4)
        private java.math.BigDecimal amount;

        @Column(nullable = false, length = 3)
        private String currency;

        @Column(nullable = false, length = 50)
        private String type;

        @Column(nullable = false, length = 20)
        private String status;

        @Column(name = "fraud_check_status", nullable = false, length = 20)
        private String fraudCheckStatus;

        @Column(name = "merchant_id", nullable = false, length = 100)
        private String merchantId;

        @Column(length = 500)
        private String description;

        @Column(name = "reference_number", unique = true, length = 100)
        private String referenceNumber;

        @Column(name = "created_at", nullable = false)
        private java.time.Instant createdAt;

        @Column(name = "updated_at", nullable = false)
        private java.time.Instant updatedAt;

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getAccountId() { return accountId; }
        public void setAccountId(UUID accountId) { this.accountId = accountId; }
        public java.math.BigDecimal getAmount() { return amount; }
        public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
        public String getCurrency() { return currency; }
        public void setCurrency(String currency) { this.currency = currency; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public String getFraudCheckStatus() { return fraudCheckStatus; }
        public void setFraudCheckStatus(String fraudCheckStatus) { this.fraudCheckStatus = fraudCheckStatus; }
        public String getMerchantId() { return merchantId; }
        public void setMerchantId(String merchantId) { this.merchantId = merchantId; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getReferenceNumber() { return referenceNumber; }
        public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
        public java.time.Instant getCreatedAt() { return createdAt; }
        public void setCreatedAt(java.time.Instant createdAt) { this.createdAt = createdAt; }
        public java.time.Instant getUpdatedAt() { return updatedAt; }
        public void setUpdatedAt(java.time.Instant updatedAt) { this.updatedAt = updatedAt; }
    }

    interface TransactionJpaRepository extends JpaRepository<TransactionEntity, UUID> {
        List<TransactionEntity> findByAccountId(UUID accountId);
        List<TransactionEntity> findByStatus(String status);
        List<TransactionEntity> findByFraudCheckStatus(String fraudCheckStatus);
        List<TransactionEntity> findByMerchantId(String merchantId);
    }
}