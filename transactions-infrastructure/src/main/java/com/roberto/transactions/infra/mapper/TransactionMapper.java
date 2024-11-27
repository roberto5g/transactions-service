package com.roberto.transactions.infra.mapper;

import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.infra.persistence.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "account.accountId", target = "accountId")
    @Mapping(source = "operationType.operationTypeId", target = "operationTypeId")
    Transaction toTransaction(TransactionEntity transactionEntity);
}

