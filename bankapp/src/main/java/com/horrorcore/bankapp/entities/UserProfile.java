package com.horrorcore.bankapp.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
import java.util.UUID;

@Document
@Data
public class UserProfile {
    @Id
    private UUID id;
    private UUID userCredentialId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;

    @ReadOnlyProperty
    @DocumentReference(lookup = "userProfileId")
    private List<Account> accounts;
}
