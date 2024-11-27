package com.roberto.transactions.rest.mapper;

import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.rest.dto.request.TransactionRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransactionMapperTest {

    @Test
    void shouldMapTransactionRequestToTransaction() {
        var request = TransactionRequest.builder()
                .accountId(1L)
                .operationTypeId(2L)
                .amount(new BigDecimal("100.00"))
                .build();

        var transaction = TransactionMapper.INSTANCE.toTransaction(request);

        assertNotNull(transaction);
        assertEquals(request.getAccountId(), transaction.getAccountId());
        assertEquals(request.getOperationTypeId(), transaction.getOperationTypeId());
        assertEquals(request.getAmount(), transaction.getAmount());
    }

    @Test
    void shouldMapTransactionToTransactionResponse() {
        var transaction = new Transaction();
        transaction.setAccountId(1L);
        transaction.setOperationTypeId(2L);
        transaction.setAmount(new BigDecimal("50.00"));

        var response = TransactionMapper.INSTANCE.toTransactionResponse(transaction);

        assertNotNull(response);
        assertEquals(transaction.getAccountId(), response.getAccountId());
        assertEquals(transaction.getOperationTypeId(), response.getOperationTypeId());
        assertEquals(transaction.getAmount(), response.getAmount());
    }

    @Test
    void shouldReturnNullWhenMappingNullRequest() {
        var transaction = TransactionMapper.INSTANCE.toTransaction(null);
        assertNull(transaction);

        var response = TransactionMapper.INSTANCE.toTransactionResponse(null);
        assertNull(response);
    }
}

