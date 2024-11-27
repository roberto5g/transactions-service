package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.enums.OperationTypeEnum;
import com.roberto.transactions.domain.core.exceptions.InvalidTransactionAmountException;
import com.roberto.transactions.domain.core.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class TransactionValidationUseCase {

    public void validate(Transaction transaction) {
        log.info("Validating operation type");
        final var operationType = OperationTypeEnum.fromCode(transaction.getOperationTypeId());

        if (operationType.requiresPositiveAmount() && transaction.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            log.error("The {} operation type requires a positive value", operationType.getDescription());
            throw new InvalidTransactionAmountException();
        } else if (!operationType.requiresPositiveAmount() && transaction.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            log.error("The {} operation type requires a negative value", operationType.getDescription());
            throw new InvalidTransactionAmountException();
        }
        log.info("Operation type validated");
    }
}