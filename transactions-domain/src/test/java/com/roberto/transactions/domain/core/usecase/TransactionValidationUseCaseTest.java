package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.enums.OperationTypeEnum;
import com.roberto.transactions.domain.core.exceptions.InvalidTransactionAmountException;
import com.roberto.transactions.domain.core.exceptions.OperationTypeNotFoundException;
import com.roberto.transactions.domain.core.models.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionValidationUseCaseTest {

    private final TransactionValidationUseCase transactionValidationUseCase = new TransactionValidationUseCase();

    @Test
    void shouldValidatePositiveAmountForPositiveOperationType() {
        var transaction = new Transaction();
        transaction.setOperationTypeId(OperationTypeEnum.CREDIT_VOUCHER.getCode());
        transaction.setAmount(new BigDecimal("100.00"));

        assertDoesNotThrow(() -> transactionValidationUseCase.validate(transaction));
    }

    @Test
    void shouldValidateNegativeAmountForNegativeOperationType() {
        var transaction = new Transaction();
        transaction.setOperationTypeId(OperationTypeEnum.WITHDRAWAL.getCode());
        transaction.setAmount(new BigDecimal("-50.00"));

        assertDoesNotThrow(() -> transactionValidationUseCase.validate(transaction));
    }

    @Test
    void shouldThrowExceptionForPositiveAmountWithNegativeOperationType() {
        var transaction = new Transaction();
        transaction.setOperationTypeId(OperationTypeEnum.WITHDRAWAL.getCode());
        transaction.setAmount(new BigDecimal("50.00"));

        var exception = assertThrows(InvalidTransactionAmountException.class,
                () -> transactionValidationUseCase.validate(transaction));

        assertNotNull(exception);
    }

    @Test
    void shouldThrowExceptionForNegativeAmountWithPositiveOperationType() {
        var transaction = new Transaction();
        transaction.setOperationTypeId(OperationTypeEnum.CREDIT_VOUCHER.getCode()); // Supondo que PAYMENT requer valor positivo
        transaction.setAmount(new BigDecimal("-100.00"));

        var exception = assertThrows(InvalidTransactionAmountException.class,
                () -> transactionValidationUseCase.validate(transaction));

        assertNotNull(exception);
    }

    @Test
    void shouldThrowExceptionWhenOperationTypeIsInvalid() {
        var transaction = new Transaction();
        transaction.setOperationTypeId(9999L);
        transaction.setAmount(new BigDecimal("100.00"));

        var exception = assertThrows(OperationTypeNotFoundException.class,
                () -> transactionValidationUseCase.validate(transaction));

        assertNotNull(exception);
    }
}
