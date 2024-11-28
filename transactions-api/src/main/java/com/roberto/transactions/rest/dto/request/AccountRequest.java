package com.roberto.transactions.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.roberto.transactions.domain.core.exceptions.errors.Errors;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest {

    @NotEmpty(message = Errors.DOCUMENT_NUMBER_REQUIRED)
    @Pattern(regexp = "^[0-9]{11}$", message = Errors.DOCUMENT_NUMBER_INVALID)
    @JsonProperty("document_number")
    private String documentNumber;
    @NotNull
    @JsonProperty("available_credit_limit")
    private BigDecimal availableCreditLimit;

}