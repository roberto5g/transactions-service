package com.roberto.transactions.rest.fixture;

import com.roberto.transactions.rest.dto.request.TransactionRequest;
import com.roberto.transactions.rest.dto.response.TransactionResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionResponseFixture {
    public static TransactionResponse withDefaultValues() {
        return TransactionResponse.builder()
                .accountId(1L)
                .operationTypeId(1L)
                .amount(BigDecimal.valueOf(1))
                .build();
    }
}
