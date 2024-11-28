package com.roberto.transactions.infra.persistence.entity;

import com.roberto.transactions.domain.core.fixtures.AccountFixture;
import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.infra.mapper.AccountMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AccountEntityTest {

    @Test
    void shouldCreateAccountEntityWithAllArgsConstructor() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);

        assertEquals(1L, accountEntity.getAccountId());
        assertEquals("12345678901", accountEntity.getDocumentNumber());
    }

    @Test
    void shouldSetAndGetFields() {
        var account = new AccountEntity();
        account.setAccountId(1L);
        account.setDocumentNumber("12345678901");

        assertEquals(1L, account.getAccountId());
        assertEquals("12345678901", account.getDocumentNumber());
    }

    @Test
    void shouldVerifyEqualsAndHashCode() {
        var account1 = AccountMapper.INSTANCE.toAccountEntity(new Account(1L, "12345678901", BigDecimal.ZERO));
        var account2 = AccountMapper.INSTANCE.toAccountEntity(new Account(1L, "12345678901", BigDecimal.ZERO));
        var account3 = AccountMapper.INSTANCE.toAccountEntity(new Account(2L, "98765432109", BigDecimal.ZERO));

        assertEquals(account1, account2);
        assertEquals(account1.hashCode(), account2.hashCode());

        assertNotEquals(account1, account3);
        assertNotEquals(account1.hashCode(), account3.hashCode());
    }


    @Test
    void shouldCreateAccountEntityWithBuilder() {
        AccountEntity account = AccountEntity.builder()
                .accountId(1L)
                .documentNumber("12345678901")
                .availableCreditLimit(BigDecimal.ZERO)
                .build();

        assertEquals(1L, account.getAccountId());
        assertEquals("12345678901", account.getDocumentNumber());
    }
}
