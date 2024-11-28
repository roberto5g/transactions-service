package com.roberto.transactions.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.roberto.transactions.domain.core.exceptions.errors.Errors;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = Errors.ACCOUNT_ID_REQUIRED)
    @Min(value = 1, message = Errors.ACCOUNT_ID_MIN_VALUE)
    @JsonProperty("account_id")
    private Long accountId;
    @NotNull(message = Errors.OPERATION_TYPE_REQUIRED)
    @Min(value = 1, message = Errors.OPERATION_TYPE_MIN_VALUE)
    @JsonProperty("operation_type_id")
    private Long operationTypeId;
    @NotNull(message = Errors.AMOUNT_REQUIRED)
    private BigDecimal amount;
}
