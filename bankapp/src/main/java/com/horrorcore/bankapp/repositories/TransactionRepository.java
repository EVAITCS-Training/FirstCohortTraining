package com.horrorcore.bankapp.repositories;

import com.horrorcore.bankapp.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends MongoRepository<Transaction, UUID> {
    List<Transaction> findAllByFromAccountIdOrToAccountId(UUID fromAccountId, UUID toAccountId);
}
