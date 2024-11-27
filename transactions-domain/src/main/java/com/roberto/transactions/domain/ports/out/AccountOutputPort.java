package com.roberto.transactions.domain.ports.out;

import com.roberto.transactions.domain.core.models.Account;

public interface AccountOutputPort {
    Account insert(Account account);
    Account findById(Long accountId);
}
