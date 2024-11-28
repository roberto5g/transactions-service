package com.roberto.transactions.infra.persistence.repositories;

import com.roberto.transactions.domain.core.models.Account;
import com.roberto.transactions.infra.persistence.entity.AccountEntity;
import com.roberto.transactions.infra.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByAccount(AccountEntity account);
}
