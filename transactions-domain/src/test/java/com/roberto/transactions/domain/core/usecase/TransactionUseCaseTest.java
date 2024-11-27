package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.fixtures.TransactionFixture;
import com.roberto.transactions.domain.ports.out.TransactionOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionUseCaseTest {

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    @Mock
    private TransactionOutputPort transactionOutputPort;

    @Mock
    private TransactionValidationUseCase transactionValidationUseCase;

    @Test
    void shouldInsertTransactionSuccessfully() {
        var transaction = TransactionFixture.createTransactionWithDefaultValues();

        when(transactionOutputPort.insert(transaction)).thenReturn(transaction);

        var result = transactionUseCase.insert(transaction);

        assertNotNull(result);
        assertEquals(transaction.getAccountId(), result.getAccountId());
        assertEquals(transaction.getOperationTypeId(), result.getOperationTypeId());
        assertEquals(transaction.getAmount(), result.getAmount());
        verify(transactionValidationUseCase, times(1)).validate(transaction);
        verify(transactionOutputPort, times(1)).insert(transaction);
    }

    @Test
    void shouldThrowExceptionWhenValidationFails() {
        var transaction = TransactionFixture.createTransactionWithDefaultValues();
        doThrow(new IllegalArgumentException("Invalid transaction"))
                .when(transactionValidationUseCase)
                .validate(transaction);

        assertThrows(IllegalArgumentException.class, () -> transactionUseCase.insert(transaction));
        verify(transactionValidationUseCase, times(1)).validate(transaction);
        verify(transactionOutputPort, never()).insert(transaction);
    }
}
