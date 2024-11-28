package com.roberto.transactions.infra.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@EqualsAndHashCode(of = "accountId")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "document_number", nullable = false, updatable = false, length = 11)
    private String documentNumber;
    @PositiveOrZero
    @Column(name = "available_credit_limit", nullable = false, scale = 2, precision = 15)
    private BigDecimal availableCreditLimit = BigDecimal.ZERO;
}
