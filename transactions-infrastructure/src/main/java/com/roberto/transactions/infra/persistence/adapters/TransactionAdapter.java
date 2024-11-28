package com.roberto.transactions.infra.persistence.adapters;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.exceptions.OperationTypeNotFoundException;
import com.roberto.transactions.domain.core.exceptions.TransactionNotAllowedException;
import com.roberto.transactions.domain.core.exceptions.TransactionPersistenceException;
import com.roberto.transactions.domain.core.models.Transaction;
import com.roberto.transactions.domain.ports.out.TransactionOutputPort;
import com.roberto.transactions.infra.mapper.TransactionMapper;
import com.roberto.transactions.infra.persistence.entity.TransactionEntity;
import com.roberto.transactions.infra.persistence.repositories.AccountJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.OperationTypeJpaRepository;
import com.roberto.transactions.infra.persistence.repositories.TransactionJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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

            if(transaction.getAmount().signum() < 0){
                var debitAmount = transaction.getAmount().abs();
                if(accountEntity.getAvailableCreditLimit().compareTo(debitAmount) >= 0){
                    accountEntity.setAvailableCreditLimit(accountEntity.getAvailableCreditLimit().subtract(debitAmount));
                } else {
                    throw new TransactionNotAllowedException();
                }
            } else {
                accountEntity.setAvailableCreditLimit(accountEntity.getAvailableCreditLimit().add(transaction.getAmount()));
            }


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
            var transactionCreated = transactionJpaRepository.save(transactionEntity);
            accountJpaRepository.save(accountEntity);
            return TransactionMapper.INSTANCE.toTransaction(transactionCreated);
        } catch (DataAccessException ex){
            log.error("Failed to save transaction to the database", ex);
            throw new TransactionPersistenceException();
        }
    }
}
