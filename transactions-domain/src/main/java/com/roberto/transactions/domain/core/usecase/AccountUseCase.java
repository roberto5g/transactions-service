package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.domain.ports.in.AccountInputPort;
import com.roberto.transactions.domain.ports.out.AccountOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountUseCase implements AccountInputPort {

    private final AccountOutputPort accountOutputPort;

    @Override
    public Account insert(Account account) {
        return accountOutputPort.insert(account);
    }

    @Override
    public Account findById(Long accountId) {
        return accountOutputPort.findById(accountId);
    }
}
