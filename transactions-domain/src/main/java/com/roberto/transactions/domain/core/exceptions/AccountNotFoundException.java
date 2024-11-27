package com.roberto.transactions.domain.core.exceptions;

import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException() {
        super(HttpStatus.NOT_FOUND, ErrorCodeEnum.TS404001);
    }
}