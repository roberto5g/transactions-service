package com.roberto.transactions.infra.persistence.adapters;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.exceptions.OperationTypeNotFoundException;
import com.roberto.transactions.domain.core.exceptions.TransactionNotAllowedException;
import com.roberto.transactions.domain.core.exceptions.TransactionPersistenceException;
import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.infra.persistence.entity.AccountEntity;
import com.roberto.transactions.infra.persistence.entity.OperationTypeEntity;
import com.roberto.transactions.infra.persistence.entity.TransactionEntity;
import com.roberto.transactions.infra.persistence.repositories.AccountJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.OperationTypeJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.TransactionJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionAdapterTest {

    @InjectMocks
    private TransactionAdapter transactionAdapter;

    @Mock
    private TransactionJpaRepository transactionJpaRepository;

    @Mock
    private AccountJpaRepository accountJpaRepository;

    @Mock
    private OperationTypeJpaRepository operationTypeJpaRepository;

    @Test
    void shouldInsertTransactionSuccessfully() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(100.0));
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");
        var operationTypeEntity = new OperationTypeEntity();
        operationTypeEntity.setOperationTypeId(1L);
        operationTypeEntity.setDescription("Normal Purchase");
        var transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionId(1L);
        transactionEntity.setAccount(accountEntity);
        transactionEntity.setOperationType(operationTypeEntity);
        transactionEntity.setAmount(BigDecimal.valueOf(100.0));
        transactionEntity.setEventDate(LocalDateTime.now());

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(operationTypeJpaRepository.findById(1L)).thenReturn(Optional.of(operationTypeEntity));
        when(transactionJpaRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);

        var result = transactionAdapter.insert(transaction);

        assertNotNull(result);
        assertEquals(transaction.getAmount(), result.getAmount());
        assertEquals(transaction.getAccountId(), result.getAccountId());
        assertEquals(transaction.getOperationTypeId(), result.getOperationTypeId());
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(operationTypeJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void shouldThrowAccountNotFoundExceptionWhenAccountDoesNotExist() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(100.0));

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(AccountNotFoundException.class, () -> transactionAdapter.insert(transaction));
        assertNotNull(exception);
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(operationTypeJpaRepository, never()).findById(anyLong());
        verify(transactionJpaRepository, never()).save(any());
    }

    @Test
    void shouldThrowOperationTypeNotFoundExceptionWhenOperationTypeDoesNotExist() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(100.0));
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(operationTypeJpaRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(OperationTypeNotFoundException.class, () -> transactionAdapter.insert(transaction));
        assertNotNull(exception);
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(operationTypeJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, never()).save(any());
    }

    @Test
    void shouldThrowTransactionPersistenceExceptionWhenDatabaseErrorOccurs() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(100.0));
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");
        var operationTypeEntity = new OperationTypeEntity();
        operationTypeEntity.setOperationTypeId(1L);
        operationTypeEntity.setDescription("Normal Purchase");

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(operationTypeJpaRepository.findById(1L)).thenReturn(Optional.of(operationTypeEntity));
        when(transactionJpaRepository.save(any(TransactionEntity.class)))
                .thenThrow(new DataAccessException("Database error") {});

        var exception = assertThrows(TransactionPersistenceException.class, () -> transactionAdapter.insert(transaction));
        assertNotNull(exception);
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(operationTypeJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void shouldDeductDebitAmountFromAvailableCreditLimitSuccessfully() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(-50.0));
        var operationType = new OperationTypeEntity();
        operationType.setOperationTypeId(1L);
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");
        accountEntity.setAvailableCreditLimit(BigDecimal.valueOf(100.0));

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(operationTypeJpaRepository.findById(1L)).thenReturn(Optional.of(operationType));

        transactionAdapter.insert(transaction);

        assertEquals(BigDecimal.valueOf(50.0), accountEntity.getAvailableCreditLimit());
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void shouldThrowTransactionNotAllowedExceptionWhenDebitAmountExceedsAvailableCreditLimit() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(-150.0));
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");
        accountEntity.setAvailableCreditLimit(BigDecimal.valueOf(100.0));

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));

        var exception = assertThrows(TransactionNotAllowedException.class, () -> transactionAdapter.insert(transaction));
        assertNotNull(exception);
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, never()).save(any(TransactionEntity.class));
    }

    @Test
    void shouldAddCreditAmountToAvailableCreditLimitSuccessfully() {
        var transaction = new Transaction(1L, 1L, BigDecimal.valueOf(50.0));
        var operationType = new OperationTypeEntity();
        operationType.setOperationTypeId(4L);
        var accountEntity = new AccountEntity();
        accountEntity.setAccountId(1L);
        accountEntity.setDocumentNumber("12345678901");
        accountEntity.setAvailableCreditLimit(BigDecimal.valueOf(100.0));

        when(accountJpaRepository.findById(1L)).thenReturn(Optional.of(accountEntity));
        when(operationTypeJpaRepository.findById(1L)).thenReturn(Optional.of(operationType));
        transactionAdapter.insert(transaction);

        assertEquals(BigDecimal.valueOf(150.0), accountEntity.getAvailableCreditLimit());
        verify(accountJpaRepository, times(1)).findById(1L);
        verify(transactionJpaRepository, times(1)).save(any(TransactionEntity.class));
    }

}
