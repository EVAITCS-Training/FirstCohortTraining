package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.Transaction;
import com.horrorcore.bankapp.entities.TransactionStatus;
import com.horrorcore.bankapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Service class responsible for handling transactions in the banking application.
 * Transactions are immutable and cannot be deleted or modified manually.
 * This class ensures proper processing and auditing of transactions.
 */
@Service
public class TransactionService {

    // Repository for accessing and managing transaction data in the database
    private final TransactionRepository transactionRepository;

    // Service for handling account-related operations
    private final AccountService accountService;

    /**
     * Constructor for TransactionService.
     *
     * @param transactionRepository Repository for transaction data.
     * @param accountService Service for account operations.
     */
    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    /**
     * Creates a new transaction, assigns it a unique ID and timestamp,
     * and processes it asynchronously.
     *
     * @param transaction The transaction to be created.
     * @return The saved transaction.
     */
    public Transaction createTransaction(Transaction transaction) {
        // Set the transaction ID and timestamp
        transaction.setId(UUID.randomUUID());
        transaction.setTimestamp(LocalDateTime.now());

        // Process the transaction asynchronously
        Runnable transactionProcessor = () -> processTransaction(transaction);
        Thread transactionThread = new Thread(transactionProcessor);
        transactionThread.setName("TransactionProcessorThread");
        transactionThread.setDaemon(true);
        transactionThread.start();

        // Save the transaction to the database
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves all transactions associated with a specific account ID.
     *
     * @param accountId The ID of the account.
     * @return A list of transactions involving the account.
     */
    public List<Transaction> findAllByAccountId(UUID accountId) {
        return transactionRepository.findAllByFromAccountIdOrToAccountId(accountId, accountId);
    }

    /**
     * Processes a transaction by performing the necessary operations
     * (e.g., deposit or withdrawal) and updating its status.
     *
     * @param transaction The transaction to be processed.
     */
    private void processTransaction(Transaction transaction) {
        try{
            // Simulate a delay to mimic real-world transaction processing
            Thread.sleep(60 * 1000);

            // Handle transaction based on its type
            switch (transaction.getType()) {
                case DEPOSIT -> {
                    try {
                        // Withdraw from the source account if applicable
                        if (transaction.getFromAccountId() != null) {
                            accountService.withdraw(transaction.getFromAccountId(), transaction.getAmount());
                        }
                        // Deposit into the destination account
                        accountService.deposit(transaction.getToAccountId(), transaction.getAmount());
                    } catch (Exception e) {
                        // Mark the transaction as denied in case of failure
                        transaction.setStatus(TransactionStatus.DENIED);
                        transactionRepository.save(transaction);
                        throw new RuntimeException("Transaction failed: " + e.getMessage());
                    }
                }
                case WITHDRAWAL -> {
                    try {
                        // Withdraw from the source account
                        accountService.withdraw(transaction.getFromAccountId(), transaction.getAmount());
                    } catch (Exception e) {
                        // Mark the transaction as denied in case of failure
                        transaction.setStatus(TransactionStatus.DENIED);
                        transactionRepository.save(transaction);
                        throw new RuntimeException("Transaction failed: " + e.getMessage());
                    }
                }
            }

            // Mark the transaction as approved upon successful processing
            transaction.setStatus(TransactionStatus.APPROVED);
            transactionRepository.save(transaction);
        } catch (InterruptedException e) {
            // Handle interruptions and mark the transaction as denied
            transaction.setStatus(TransactionStatus.DENIED);
            transactionRepository.save(transaction);
            throw new RuntimeException(e);
        }
    }
}
