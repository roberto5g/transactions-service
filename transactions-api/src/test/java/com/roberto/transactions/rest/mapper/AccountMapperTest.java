package com.roberto.transactions.rest.mapper;

import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.rest.dto.request.AccountRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountMapperTest {

    @Test
    void shouldMapAccountRequestToAccount() {
        var request = AccountRequest.builder()
                .documentNumber("12345678901")
                .availableCreditLimit(BigDecimal.valueOf(100))
                .build();

        var account = AccountMapper.INSTANCE.toAccount(request);

        assertNotNull(account);
        assertEquals(request.getDocumentNumber(), account.getDocumentNumber());
        assertEquals(request.getAvailableCreditLimit(), account.getAvailableCreditLimit());
    }

    @Test
    void shouldMapAccountToAccountResponse() {
        var account = new Account();
        account.setAccountId(1L);
        account.setDocumentNumber("98765432109");
        account.setAvailableCreditLimit(BigDecimal.valueOf(100));

        var response = AccountMapper.INSTANCE.toAccountResponse(account);

        assertNotNull(response);
        assertEquals(account.getAccountId(), response.getAccountId());
        assertEquals(account.getDocumentNumber(), response.getDocumentNumber());
        assertEquals(account.getAvailableCreditLimit(), response.getAvailableCreditLimit());
    }

    @Test
    void shouldReturnNullWhenMappingNullRequest() {
        assertNull(AccountMapper.INSTANCE.toAccount(null));
        assertNull(AccountMapper.INSTANCE.toAccountResponse(null));
    }
}

