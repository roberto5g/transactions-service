package com.roberto.transactions.rest.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountTransactionsResponse {
    private Long accountId;
    private String documentNumber;
    private List<TransactionResponse> transactions;
}

