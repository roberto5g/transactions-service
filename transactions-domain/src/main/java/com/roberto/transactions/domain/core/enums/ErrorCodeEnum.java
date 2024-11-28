package com.roberto.transactions.domain.core.enums;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ErrorCodeEnum {
    TS400001,
    TS400002,
    TS400003,
    TS404001,
    TS404002,
    TS500001;

    public String getMessage(final Locale messageLocale){
        return ResourceBundle.getBundle("messages/exceptions", messageLocale).getString(this.name()+".message");
    }
}
