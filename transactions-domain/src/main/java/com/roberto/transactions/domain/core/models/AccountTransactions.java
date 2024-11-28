package com.roberto.transactions.domain.core.models;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransactions {
    private Account account;
    private List<Transaction> transactions;
}