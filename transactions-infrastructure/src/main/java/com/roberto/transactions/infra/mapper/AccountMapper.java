package com.roberto.transactions.infra.mapper;

import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.infra.persistence.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountEntity toAccountEntity(Account account);
    Account toAccount(AccountEntity accountEntity);

    default Account toAccount(Optional<AccountEntity> accountEntityOptional) {
        return accountEntityOptional.map(this::toAccount).orElse(null);
    }
}
