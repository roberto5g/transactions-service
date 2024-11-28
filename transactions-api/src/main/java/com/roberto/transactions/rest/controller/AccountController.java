package com.roberto.transactions.rest.controller;

import com.roberto.transactions.infra.handler.ExceptionResponse;
import com.roberto.transactions.domain.ports.in.AccountInputPort;
import com.roberto.transactions.rest.dto.request.AccountRequest;
import com.roberto.transactions.rest.dto.response.AccountResponse;
import com.roberto.transactions.rest.mapper.AccountMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Endpoints for managing accounts")
public class AccountController {

    private final AccountInputPort accountInputPort;

    @Operation(
            summary = "Create a new account",
            description = "Creates a new account with the provided details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Account created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request data",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody AccountRequest accountRequest) {
        log.info("Creating a new account");
        var ac = AccountMapper.INSTANCE.toAccount(accountRequest);
        var account = accountInputPort.insert(AccountMapper.INSTANCE.toAccount(accountRequest));
        log.info("Account created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountMapper.INSTANCE.toAccountResponse(account));
    }

    @Operation(
            summary = "Get account details",
            description = "Fetches details of an account by its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Account not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
            )
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable("accountId") final Long id) {
        log.info("Fetching account with ID: {}", id);
        final var account = accountInputPort.findById(id);
        log.info("Account retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(AccountMapper.INSTANCE.toAccountResponse(account));
    }
}
