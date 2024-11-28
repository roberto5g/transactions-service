package com.roberto.transactions.domain.core.exceptions;

import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class TransactionNotAllowedException extends BusinessException {
    public TransactionNotAllowedException() {
        super(HttpStatus.BAD_REQUEST, ErrorCodeEnum.TS400003);
    }
}
