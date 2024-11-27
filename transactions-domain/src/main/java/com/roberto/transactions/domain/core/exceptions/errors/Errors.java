package com.roberto.transactions.domain.core.exceptions.errors;

public class Errors {

    public static final String DOCUMENT_NUMBER_REQUIRED = "{documentNumber.required}";
    public static final String DOCUMENT_NUMBER_INVALID = "{documentNumber.invalid}";
    public static final String ACCOUNT_ID_REQUIRED = "{accountId.required}";
    public static final String ACCOUNT_ID_MIN_VALUE = "{accountId.min.value}";
    public static final String OPERATION_TYPE_MIN_VALUE = "{operation.type.min.value}";
    public static final String OPERATION_TYPE_REQUIRED = "{operation.type.required}";
    public static final String AMOUNT_REQUIRED = "{amount.required}";

    private Errors() {
    }
}
