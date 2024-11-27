package com.roberto.transactions.rest.fixture;

import com.roberto.transactions.rest.dto.request.AccountRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRequestFixture {
    public static AccountRequest withDefaultValues() {
        return AccountRequest.builder().documentNumber("12345678901").build();
    }

}
