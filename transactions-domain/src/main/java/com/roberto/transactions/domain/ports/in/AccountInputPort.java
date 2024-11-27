package com.roberto.transactions.domain.ports.in;

import com.roberto.transactions.domain.core.models.Account;

public interface AccountInputPort {
    Account insert(final Account account);
    Account findById(final Long accountId);
}
