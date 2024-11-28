package com.roberto.transactions.rest.mapper;

import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.rest.dto.request.TransactionRequest;
import com.roberto.transactions.rest.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {

    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(source = "accountId", target = "accountId")
    @Mapping(source = "operationTypeId", target = "operationTypeId")
    @Mapping(source = "amount", target = "amount")
    Transaction toTransaction(TransactionRequest transactionRequest);
    TransactionResponse toTransactionResponse(Transaction transaction);

    List<TransactionResponse> toTransactionResponseList(List<Transaction> transactions);
}
