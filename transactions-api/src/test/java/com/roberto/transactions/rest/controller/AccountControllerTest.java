package com.roberto.transactions.rest.controller;

import com.roberto.transactions.domain.core.exceptions.AccountNotFoundException;
import com.roberto.transactions.domain.core.exceptions.errors.Errors;
import com.roberto.transactions.domain.core.fixtures.AccountFixture;
import com.roberto.transactions.domain.ports.in.AccountInputPort;
import com.roberto.transactions.rest.dto.request.AccountRequest;
import com.roberto.transactions.rest.fixture.AccountRequestFixture;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @InjectMocks
    AccountController accountController;

    @Mock
    AccountInputPort accountInputPort;

    private Validator validator;

    @BeforeEach
    void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldReturnAccountResponseWithSuccess() {
        var account = AccountFixture.createAccountWithDefaultValues();
        when(accountInputPort.findById(1L)).thenReturn(account);
        final var response = accountController.getAccount(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void shouldReturnAccountNotFoundExceptionWhenAccountNotExist() {
        when(accountInputPort.findById(1L)).thenThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> accountController.getAccount(1L));
    }

    @Test
    void shouldCreateAccountSuccess() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountRequest = AccountRequestFixture.withDefaultValues();
        when(accountInputPort.insert(any())).thenReturn(account);

        final var response = accountController.create(accountRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account.getAccountId(), Objects.requireNonNull(response.getBody()).getAccountId());
        assertEquals(account.getDocumentNumber(), Objects.requireNonNull(response.getBody()).getDocumentNumber());
    }

    @Test
    void shouldReturnValidationErrorWhenDocumentNumberIsEmpty() {
        var accountRequest = AccountRequest.builder()
                .documentNumber("")
                .build();

        var violations = validator.validate(accountRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.DOCUMENT_NUMBER_REQUIRED)));
    }

    @Test
    void shouldReturnValidationErrorWhenDocumentNumberExceedsMaxSize() {

        var accountRequest = AccountRequest.builder()
                .documentNumber("123456789012")
                .build();

        var violations = validator.validate(accountRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.DOCUMENT_NUMBER_INVALID)));
    }

    @Test
    void shouldReturnValidationErrorWhenDocumentNumberIsInvalid() {
        var accountRequest = AccountRequest.builder()
                .documentNumber("123ABC78901")
                .build();

        var violations = validator.validate(accountRequest);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
                .anyMatch(v -> v.getMessage().equals(Errors.DOCUMENT_NUMBER_INVALID)));
    }

    @Test
    void shouldCreateAccountWhenRequestIsValid() {
        var account = AccountFixture.createAccountWithDefaultValues();
        var accountRequest = AccountRequest.builder()
                .build();
        accountRequest.setDocumentNumber("12345678901");

        when(accountInputPort.insert(any())).thenReturn(account);

        final var response = accountController.create(accountRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account.getDocumentNumber(), Objects.requireNonNull(response.getBody()).getDocumentNumber());
    }

}
