package com.roberto.transactions.rest.fixture;

import com.roberto.transactions.rest.dto.request.TransactionRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionRequestFixture {
    public static TransactionRequest withDefaultValues() {
        return TransactionRequest.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(100.00))
                .build();
    }
}
