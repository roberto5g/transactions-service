package com.roberto.transactions.rest.mapper;

import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.rest.dto.request.AccountRequest;
import com.roberto.transactions.rest.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountRequest accountRequest);
    AccountResponse toAccountResponse(Account account);
}
