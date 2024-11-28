package com.roberto.transactions.rest.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountResponseTest {

    @Test
    void shouldCreateAccountResponseUsingBuilder() {
        var accountResponse = AccountResponse.builder()
                .accountId(1L)
                .documentNumber("12345678901")
                .availableCreditLimit(BigDecimal.ZERO)
                .build();

        assertNotNull(accountResponse);
        assertEquals(1L, accountResponse.getAccountId());
        assertEquals("12345678901", accountResponse.getDocumentNumber());
    }

    @Test
    void shouldSetAndGetFieldsSuccessfully() {
        var accountResponse = new AccountResponse();

        accountResponse.setAccountId(2L);
        accountResponse.setDocumentNumber("98765432109");

        assertEquals(2L, accountResponse.getAccountId());
        assertEquals("98765432109", accountResponse.getDocumentNumber());
    }

    @Test
    void shouldCreateAccountResponseUsingAllArgsConstructor() {
        var accountResponse = new AccountResponse(3L, "11122233344", BigDecimal.ZERO);

        assertNotNull(accountResponse);
        assertEquals(3L, accountResponse.getAccountId());
        assertEquals("11122233344", accountResponse.getDocumentNumber());
    }
}

