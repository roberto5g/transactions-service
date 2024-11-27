package com.roberto.transactions.domain.ports.in;

import com.roberto.transactions.domain.core.models.Transaction;

public interface TransactionInputPort {
    Transaction insert(final Transaction transaction);
}

