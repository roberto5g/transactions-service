package com.roberto.transactions.infra.persistence.adapters;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.infra.mapper.AccountMapper;
import com.roberto.transactions.infra.persistence.repositories.AccountJpaRepository;
import com.roberto.transactions.domain.ports.out.AccountOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountAdapter implements AccountOutputPort {

    private final AccountJpaRepository accountJpaRepository;

    @Override
    @Transactional
    public Account insert(final Account account) {
        final var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);
        return AccountMapper.INSTANCE.toAccount(accountJpaRepository.save(accountEntity));
    }

    @Override
    public Account findById(Long accountId) {
        final var account = accountJpaRepository.findById(accountId).orElseThrow(() -> {
            log.error("Account not found for ID: {}", accountId);
            return new AccountNotFoundException();
        });
        return AccountMapper.INSTANCE.toAccount(account);
    }

}
