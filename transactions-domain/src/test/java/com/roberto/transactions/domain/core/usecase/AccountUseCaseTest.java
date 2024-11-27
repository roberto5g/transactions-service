package com.roberto.transactions.domain.core.usecase;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.fixtures.AccountFixture;
import com.roberto.transactions.domain.ports.out.AccountOutputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountUseCaseTest {

    @InjectMocks
    private AccountUseCase accountUseCase;

    @Mock
    private AccountOutputPort accountOutputPort;

    @Test
    void shouldInsertAccountSuccessfully() {
        var account = AccountFixture.createAccountWithDefaultValues();
        when(accountOutputPort.insert(account)).thenReturn(account);

        var result = accountUseCase.insert(account);

        assertNotNull(result);
        assertEquals(account.getAccountId(), result.getAccountId());
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(accountOutputPort, times(1)).insert(account);
    }

    @Test
    void shouldFindAccountByIdSuccessfully() {
        var account = AccountFixture.createAccountWithDefaultValues();
        when(accountOutputPort.findById(1L)).thenReturn(account);

        var result = accountUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(account.getAccountId(), result.getAccountId());
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(accountOutputPort, times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFound() {
        when(accountOutputPort.findById(1L)).thenThrow(new AccountNotFoundException());

        assertThrows(AccountNotFoundException.class, () -> accountUseCase.findById(1L));
        verify(accountOutputPort, times(1)).findById(1L);
    }
}
