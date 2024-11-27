package com.roberto.transactions.rest.fixture;

import com.roberto.transactions.rest.dto.request.AccountRequest;
import com.roberto.transactions.rest.dto.response.AccountResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountResponseFixture {
    public static AccountResponse withDefaultValues() {
        return AccountResponse.builder().documentNumber("12345678901").build();
    }

}
