package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.domain.ports.in.TransactionInputPort;
import com.roberto.transactions.domain.ports.out.TransactionOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionUseCase implements TransactionInputPort {

    private final TransactionOutputPort transactionOutputPort;
    private final TransactionValidationUseCase transactionValidationUseCase;

    @Override
    public Transaction insert(Transaction transaction) {
        transactionValidationUseCase.validate(transaction);
        return transactionOutputPort.insert(transaction);
    }
}