package com.roberto.transactions.domain.core.enums;

import com.roberto.transactions.domain.core.exceptions.OperationTypeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public enum OperationTypeEnum {
    NORMAL_PURCHASE(1L, "Normal Purchase", false),
    PURCHASE_WITH_INSTALLMENTS(2L, "Purchase with installments", true),
    WITHDRAWAL(3L, "Withdrawal", false),
    CREDIT_VOUCHER(4L, "Credit Voucher", true);

    private static final Logger log = LoggerFactory.getLogger(OperationTypeEnum.class);
    private final Long code;
    private final String description;
    private final boolean requiresPositiveAmount;

    OperationTypeEnum(Long code, String description, boolean requiresPositiveAmount) {
        this.code = code;
        this.description = description;
        this.requiresPositiveAmount = requiresPositiveAmount;
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean requiresPositiveAmount() {
        return requiresPositiveAmount;
    }

    public static OperationTypeEnum fromCode(Long code) {
        for (OperationTypeEnum type : values()) {
            if (Objects.equals(type.code, code)) {
                return type;
            }
        }
        log.error("Operation type not found with operationTypeId: {}", code);
        throw new OperationTypeNotFoundException();
    }
}
