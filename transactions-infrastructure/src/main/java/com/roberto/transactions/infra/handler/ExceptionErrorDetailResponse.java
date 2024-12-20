package com.roberto.transactions.infra.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionErrorDetailResponse {
    private String message;
    private ErrorCodeEnum code;
    private Map<String, List<String>> fields;
}
