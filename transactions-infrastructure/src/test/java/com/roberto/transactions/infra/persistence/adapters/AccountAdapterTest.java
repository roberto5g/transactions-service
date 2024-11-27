package com.roberto.transactions.infra.persistence.adapters;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.fixtures.AccountFixture;
import com.roberto.transactions.infra.mapper.AccountMapper;
import com.roberto.transactions.infra.persistence.repositories.AccountJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountAdapterTest {

    @InjectMocks
    private AccountAdapter accountAdapter;

    @Mock
    private AccountJpaRepository accountJpaRepository;

    @Test
    void shouldInsertAccountSuccessfully() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);

        when(accountJpaRepository.save(any())).thenReturn(accountEntity);

        var result = accountAdapter.insert(account);

        assertNotNull(result);
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(accountJpaRepository, times(1)).save(any());
    }

    @Test
    void shouldFindAccountByIdSuccessfully() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountEntity = AccountMapper.INSTANCE.toAccountEntity(account);

        when(accountJpaRepository.findById(account.getAccountId())).thenReturn(Optional.of(accountEntity));

        var result = accountAdapter.findById(account.getAccountId());
        
        assertNotNull(result);
        assertEquals(account.getAccountId(), result.getAccountId());
        assertEquals(account.getDocumentNumber(), result.getDocumentNumber());
        verify(accountJpaRepository, times(1)).findById(account.getAccountId());
    }

    @Test
    void shouldThrowExceptionWhenAccountNotFoundById() {
        Long invalidAccountId = 999L;

        when(accountJpaRepository.findById(invalidAccountId)).thenReturn(Optional.empty());

        var exception = assertThrows(AccountNotFoundException.class, () -> accountAdapter.findById(invalidAccountId));

        assertNotNull(exception);
        verify(accountJpaRepository, times(1)).findById(invalidAccountId);
    }
}
