package com.roberto.transactions.domain.core.models;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long accountId;
    private String documentNumber;
}


