package com.horrorcore.bankapp.controllers;

import com.horrorcore.bankapp.entities.Transaction;
import com.horrorcore.bankapp.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This class is a REST controller that handles HTTP requests related to transactions.
 * It provides endpoints for creating transactions and retrieving transactions by account ID.
 */
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    /**
     * Constructor for the TransactionController.
     *
     * @param transactionService The service layer that handles business logic for transactions.
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Handles HTTP POST requests to create a new transaction.
     *
     * @param transaction The transaction object sent in the request body.
     * @return A ResponseEntity containing the created transaction and an HTTP status of 201 (Created).
     */
    @PostMapping(value = {"", "/"})
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);
    }

    /**
     * Handles HTTP GET requests to retrieve all transactions for a specific account.
     *
     * @param accountId The unique identifier of the account whose transactions are to be retrieved.
     * @return A ResponseEntity containing a list of transactions and an HTTP status of 200 (OK).
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable UUID accountId) {
        List<Transaction> transactions = transactionService.findAllByAccountId(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
