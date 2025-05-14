package com.horrorcore.bankapp.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Data
public class Account {
    @Id
    private UUID id;
    private AccountType accountType;
    private double balance;
    private boolean active;
    private UUID userProfileId;
}
