package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.Transaction;
import com.horrorcore.bankapp.entities.TransactionStatus;
import com.horrorcore.bankapp.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    // Can banks delete or modify transactions manually?
    // No, banks cannot delete or modify transactions manually.
    // Transactions are immutable records of financial activity and are subject to strict regulations and auditing processes.
    // Any errors or discrepancies must be resolved through proper channels, such as issuing refunds or corrections,
    // rather than altering the original transaction records.
    // This means in our program we will not be able to delete or modify transactions.
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    public Transaction createTransaction(Transaction transaction) {
        // Set the transaction ID and timestamp
        transaction.setId(UUID.randomUUID());
        transaction.setTimestamp(LocalDateTime.now());

        // Save the transaction to the database
        Runnable transactionProcessor = () -> processTransaction(transaction);
        Thread transactionThread = new Thread(transactionProcessor);
        transactionThread.setName("TransactionProcessorThread");
        transactionThread.setDaemon(true);
        transactionThread.start();
        return transactionRepository.save(transaction);
    }

    public List<Transaction> findAllByAccountId(UUID accountId) {
        return transactionRepository.findAllByFromAccountIdOrToAccountId(accountId, accountId);
    }

    private void processTransaction(Transaction transaction) {
        try{
            Thread.sleep(60 * 1000); // Simulate a 60-second delay
            switch (transaction.getType()) {
                case DEPOSIT -> {
                    try {
                        if (transaction.getFromAccountId() != null) {
                            accountService.withdraw(transaction.getFromAccountId(), transaction.getAmount());
                        }
                        accountService.deposit(transaction.getToAccountId(), transaction.getAmount());
                    } catch (Exception e) {
                        transaction.setStatus(TransactionStatus.DENIED);
                        transactionRepository.save(transaction);
                        throw new RuntimeException("Transaction failed: " + e.getMessage());
                    }
                }
                case WITHDRAWAL -> {
                    try {
                        accountService.withdraw(transaction.getFromAccountId(), transaction.getAmount());
                    } catch (Exception e) {
                        transaction.setStatus(TransactionStatus.DENIED);
                        transactionRepository.save(transaction);
                        throw new RuntimeException("Transaction failed: " + e.getMessage());
                    }
                }
            }
            transaction.setStatus(TransactionStatus.APPROVED);
            transactionRepository.save(transaction);
        } catch (InterruptedException e) {
            transaction.setStatus(TransactionStatus.DENIED);
            transactionRepository.save(transaction);
            throw new RuntimeException(e);
        }
    }
}
