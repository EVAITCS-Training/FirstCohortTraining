package com.horrorcore.bankapp.repositories;

import com.horrorcore.bankapp.entities.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends MongoRepository<Account, UUID> {
    List<Account> findAllByUserProfileId(UUID userProfileId);
}
