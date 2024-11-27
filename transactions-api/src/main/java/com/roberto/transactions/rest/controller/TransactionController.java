package com.roberto.transactions.rest.controller;

import com.roberto.transactions.infra.handler.ExceptionResponse;
import com.roberto.transactions.domain.ports.in.TransactionInputPort;
import com.roberto.transactions.rest.dto.request.TransactionRequest;
import com.roberto.transactions.rest.dto.response.TransactionResponse;
import com.roberto.transactions.rest.mapper.TransactionMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Endpoints for managing transactions")
public class TransactionController {

    private final TransactionInputPort transactionInputPort;

    @Operation(
            summary = "Create a new transaction",
            description = "Creates a transaction based on the provided details",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Transaction created successfully",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid request data",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionRequest transactionRequest){
        log.info("Trying to create a transaction");
        final var transaction = transactionInputPort.insert(TransactionMapper.INSTANCE.toTransaction(transactionRequest));
        log.info("Transaction created successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionMapper.INSTANCE.toTransactionResponse(transaction));
    }

}
