package com.roberto.transactions.infra.persistence.entity;

import com.roberto.transactions.domain.core.fixtures.AccountFixture;
import com.roberto.transactions.infra.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionEntityTest {

    @Test
    void shouldCreateTransactionEntityWithAllArgsConstructor() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);
        var operationType = new OperationTypeEntity();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");
        var transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
        transactionEntity.setAccount(accountEntity);
        transactionEntity.setOperationType(operationType);
        transactionEntity.setAmount(BigDecimal.valueOf(100.0));
        transactionEntity.setEventDate(LocalDateTime.now());

        assertEquals(1L, transactionEntity.getTransactionId());
        assertEquals(accountEntity, transactionEntity.getAccount());
        assertEquals(operationType, transactionEntity.getOperationType());
        assertEquals(BigDecimal.valueOf(100.00), transactionEntity.getAmount());
        assertNotNull(transactionEntity.getEventDate());
    }

    @Test
    void shouldSetAndGetFields() {
        var transaction = new TransactionEntity();
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);
        var operationType = new OperationTypeEntity();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");

        transaction.setTransactionId(1L);
        transaction.setAccount(accountEntity);
        transaction.setOperationType(operationType);
        transaction.setAmount(BigDecimal.valueOf(200.00));
        transaction.setEventDate(LocalDateTime.now());

        assertEquals(1L, transaction.getTransactionId());
        assertEquals(accountEntity, transaction.getAccount());
        assertEquals(operationType, transaction.getOperationType());
        assertEquals(BigDecimal.valueOf(200.00), transaction.getAmount());
        assertNotNull(transaction.getEventDate());
    }

    @Test
    void shouldVerifyEqualsAndHashCode() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);
        var operationType = new OperationTypeEntity();
        operationType.setOperationTypeId(1L);
        operationType.setDescription("Normal Purchase");
        var now = LocalDateTime.now();

        var transaction1 = new TransactionEntity();
        transaction1.setTransactionId(1L);
        transaction1.setAccount(accountEntity);
        transaction1.setOperationType(operationType);
        transaction1.setAmount(BigDecimal.valueOf(100.0));
        transaction1.setEventDate(now);

        var transaction2 = new TransactionEntity();
        transaction2.setTransactionId(1L);
        transaction2.setAccount(accountEntity);
        transaction2.setOperationType(operationType);
        transaction2.setAmount(BigDecimal.valueOf(100.0));
        transaction2.setEventDate(now);

        var transaction3 = new TransactionEntity();
        transaction3.setTransactionId(2L);
        transaction3.setAccount(accountEntity);
        transaction3.setOperationType(operationType);
        transaction3.setAmount(BigDecimal.valueOf(50.0));
        transaction3.setEventDate(now);

        assertEquals(transaction1, transaction2);
        assertNotEquals(transaction1, transaction3);
    }
}
