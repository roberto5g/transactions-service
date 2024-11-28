package com.roberto.transactions.domain.core.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long accountId;
    private String documentNumber;
    private BigDecimal availableCreditLimit;
}


