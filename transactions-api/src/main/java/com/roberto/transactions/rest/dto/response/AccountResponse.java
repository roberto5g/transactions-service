package com.roberto.transactions.rest.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long accountId;
    private String documentNumber;
    private BigDecimal availableCreditLimit;
}
