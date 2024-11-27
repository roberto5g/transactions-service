package com.roberto.transactions.rest.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionResponseTest {

    @Test
    void shouldCreateTransactionResponseUsingBuilder() {
        var transactionResponse = TransactionResponse.builder()
                .accountId(1L)
                .operationTypeId(2L)
                .amount(new BigDecimal("100.00"))
                .build();

        assertNotNull(transactionResponse);
        assertEquals(1L, transactionResponse.getAccountId());
        assertEquals(2L, transactionResponse.getOperationTypeId());
        assertEquals(new BigDecimal("100.00"), transactionResponse.getAmount());
    }

    @Test
    void shouldSetAndGetFieldsSuccessfully() {
        var transactionResponse = new TransactionResponse();

        transactionResponse.setAccountId(1L);
        transactionResponse.setOperationTypeId(2L);
        transactionResponse.setAmount(new BigDecimal("50.00"));

        assertEquals(1L, transactionResponse.getAccountId());
        assertEquals(2L, transactionResponse.getOperationTypeId());
        assertEquals(new BigDecimal("50.00"), transactionResponse.getAmount());
    }
}

