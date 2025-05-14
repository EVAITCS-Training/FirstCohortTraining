package com.horrorcore.bankapp.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document
public class Transaction {
    private UUID id;
    private TransactionType type;
    private TransactionStatus status;
    private double amount;
    private UUID fromAccountId;
    private UUID toAccountId;
    private LocalDateTime timestamp;
}
