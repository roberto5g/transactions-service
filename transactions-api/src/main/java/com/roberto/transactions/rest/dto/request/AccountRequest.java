package com.roberto.transactions.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.roberto.transactions.domain.core.exceptions.errors.Errors;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRequest {

    @NotEmpty(message = Errors.DOCUMENT_NUMBER_REQUIRED)
    @Pattern(regexp = "^[0-9]{11}$", message = Errors.DOCUMENT_NUMBER_INVALID)
    @JsonProperty("document_number")
    private String documentNumber;

}