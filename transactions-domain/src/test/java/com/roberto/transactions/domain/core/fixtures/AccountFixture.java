package com.roberto.transactions.domain.core.fixtures;

import com.roberto.transactions.domain.core.models.Account;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountFixture {

    public static Account createAccountWithDefaultValues() {
        return Account.builder().accountId(1L).documentNumber("12345678901").build();
    }

}
