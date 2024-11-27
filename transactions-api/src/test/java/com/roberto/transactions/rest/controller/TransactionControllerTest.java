package com.roberto.transactions.rest.controller;

import com.roberto.transactions.domain.core.exceptions.errors.Errors;
import com.roberto.transactions.domain.core.fixtures.TransactionFixture;
import com.roberto.transactions.domain.ports.in.TransactionInputPort;
import com.roberto.transactions.rest.dto.request.TransactionRequest;
import com.roberto.transactions.rest.fixture.TransactionRequestFixture;
import com.roberto.transactions.rest.fixture.TransactionResponseFixture;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @InjectMocks
    TransactionController transactionController;
    @Mock
    TransactionInputPort transactionInputPort;

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldCreateTransactionSuccess() {
        var transaction = TransactionFixture.createTransactionWithDefaultValues();
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        when(transactionInputPort.insert(any())).thenReturn(transaction);

        final var response = transactionController.create(transactionRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transaction.getOperationTypeId(), Objects.requireNonNull(response.getBody()).getOperationTypeId());
        assertEquals(transaction.getAccountId(), Objects.requireNonNull(response.getBody()).getAccountId());
        assertEquals(transaction.getAmount(), Objects.requireNonNull(response.getBody()).getAmount());

    }

    @Test
    void shouldReturnValidationErrorWhenAccountIdIsNull() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        transactionRequest.setAccountId(null);


        var violations = validator.validate(transactionRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.ACCOUNT_ID_REQUIRED)));
    }

    @Test
    void shouldReturnValidationErrorWhenAccountIdIsLessThanOne() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        transactionRequest.setAccountId(0L);

        var violations = validator.validate(transactionRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.ACCOUNT_ID_MIN_VALUE)));
    }

    @Test
    void shouldReturnValidationErrorWhenOperationTypeIdIsNull() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        transactionRequest.setOperationTypeId(null);

        var violations = validator.validate(transactionRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.OPERATION_TYPE_REQUIRED)));
    }

    @Test
    void shouldReturnValidationErrorWhenOperationTypeIdIsLessThanOne() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        transactionRequest.setOperationTypeId(0L);

        var violations = validator.validate(transactionRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.OPERATION_TYPE_MIN_VALUE)));
    }

    @Test
    void shouldReturnValidationErrorWhenAmountIsNull() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();
        transactionRequest.setAmount(null);

        var violations = validator.validate(transactionRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.AMOUNT_REQUIRED)));
    }

    @Test
    void shouldPassValidationWithValidTransactionRequest() {
        var transactionRequest = TransactionRequestFixture.withDefaultValues();

        var violations = validator.validate(transactionRequest);

        assertTrue(violations.isEmpty());
    }
}
