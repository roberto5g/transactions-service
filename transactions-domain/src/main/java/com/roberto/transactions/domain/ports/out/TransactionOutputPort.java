package com.roberto.transactions.domain.ports.out;

import com.roberto.transactions.domain.core.models.AccountTransactions;
import com.roberto.transactions.domain.core.models.Transaction;

import java.util.List;

public interface TransactionOutputPort {
    Transaction insert(Transaction transaction);
    AccountTransactions getTransactionsByAccountId(Long accountId);
}
