package com.horrorcore.bankapp.repositories;

import com.horrorcore.bankapp.entities.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends MongoRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUserCredentialId(UUID id);
}
