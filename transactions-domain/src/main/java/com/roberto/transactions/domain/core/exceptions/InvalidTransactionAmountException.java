package com.roberto.transactions.domain.core.exceptions;

import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class InvalidTransactionAmountException extends BusinessException {
    public InvalidTransactionAmountException() {
        super(HttpStatus.BAD_REQUEST, ErrorCodeEnum.TS400002);
    }
}
