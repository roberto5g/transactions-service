package com.roberto.transactions.infra.persistence.adapters;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.exceptions.OperationTypeNotFoundException;
import com.roberto.transactions.domain.core.exceptions.TransactionPersistenceException;
import com.roberto.transactions.domain.core.models.AccountTransactions;
import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.infra.mapper.AccountMapper;
import com.roberto.transactions.infra.mapper.TransactionMapper;
import com.roberto.transactions.infra.persistence.entity.TransactionEntity;
import com.roberto.transactions.infra.persistence.repositories.AccountJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.OperationTypeJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.TransactionJpaRepository;
import com.roberto.transactions.domain.ports.out.TransactionOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TransactionAdapter implements TransactionOutputPort {

    private final TransactionJpaRepository transactionJpaRepository;
    private final AccountJpaRepository accountJpaRepository;
    private final OperationTypeJpaRepository operationTypeJpaRepository;

    @Override
    @Transactional
    public Transaction insert(final Transaction transaction) {
        try {
            log.info("Getting account entity");
            final var accountEntity = accountJpaRepository.findById(transaction.getAccountId())
                    .orElseThrow(() -> {
                        log.error("Account not found for ID: {}", transaction.getAccountId());
                        return new AccountNotFoundException();
                    });

            log.info("Getting operation type entity with ID: {}", transaction.getOperationTypeId());
            final var operationTypeEntity = operationTypeJpaRepository.findById(transaction.getOperationTypeId())
                    .orElseThrow(() -> {
                        log.error("Operation type not found for ID: {}", transaction.getOperationTypeId());
                        return new OperationTypeNotFoundException();
                    });

            final var transactionEntity = TransactionEntity.builder()
                    .account(accountEntity)
                    .operationType(operationTypeEntity)
                    .amount(transaction.getAmount())
                    .eventDate(LocalDateTime.now()).build();
            log.info("Saving the transaction");
            return TransactionMapper.INSTANCE.toTransaction(transactionJpaRepository.save(transactionEntity));
        } catch (DataAccessException ex){
            log.error("Failed to save transaction to the database", ex);
            throw new TransactionPersistenceException();
        }
    }

    @Override
    public AccountTransactions getTransactionsByAccountId(final Long accountId) {
        final var accountEntity = accountJpaRepository.findById(accountId)
                .orElseThrow(() -> {
                    log.error("Account not found for ID: {}", accountId);
                    return new AccountNotFoundException();
                });
        var account = AccountMapper.INSTANCE.toAccount(accountEntity);
        var transactions = TransactionMapper.INSTANCE.toTransactionList(transactionJpaRepository.findByAccount(accountEntity));
        return AccountTransactions.builder().account(account).transactions(transactions).build();
    }
}
