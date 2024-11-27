package com.roberto.transactions.domain.ports.out;

import com.roberto.transactions.domain.core.models.Transaction;

public interface TransactionOutputPort {
    Transaction insert(Transaction transaction);
}
