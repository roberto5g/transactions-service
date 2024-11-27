package com.roberto.transactions.infra.persistence.repositories;

import com.roberto.transactions.infra.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
}
