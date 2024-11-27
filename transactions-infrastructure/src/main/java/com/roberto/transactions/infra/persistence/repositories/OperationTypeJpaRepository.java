package com.roberto.transactions.infra.persistence.repositories;

import com.roberto.transactions.infra.persistence.entity.OperationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationTypeJpaRepository extends JpaRepository<OperationTypeEntity, Long> {
}
