package com.roberto.transactions.domain.core.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long accountId;
    private Long operationTypeId;
    private BigDecimal amount;
}