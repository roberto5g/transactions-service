package com.roberto.transactions.rest.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long accountId;
    private Long operationTypeId;
    private BigDecimal amount;
}
