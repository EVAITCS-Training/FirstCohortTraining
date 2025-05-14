package com.horrorcore.bankapp.repositories;

import com.horrorcore.bankapp.entities.UserCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserCredentialRepository extends MongoRepository<UserCredential, UUID> {
    Optional<UserCredential> findByUsername(String username);
}
