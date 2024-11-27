package com.roberto.transactions.domain.core.fixtures;

import com.roberto.transactions.domain.core.models.Transaction;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionFixture {

    public static Transaction createTransactionWithDefaultValues() {
        return Transaction.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(100.00))
                .build();
    }

}
