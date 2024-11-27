package com.roberto.transactions.domain.core.exceptions;

import com.roberto.transactions.domain.core.enums.ErrorCodeEnum;
import org.springframework.http.HttpStatus;

public class TransactionPersistenceException extends BusinessException {
    public TransactionPersistenceException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeEnum.TS500001);
    }
}
