package com.roberto.transactions.domain.core.exceptions;

import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class OperationTypeNotFoundException extends BusinessException {
    public OperationTypeNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCodeEnum.TS404002);
    }
}