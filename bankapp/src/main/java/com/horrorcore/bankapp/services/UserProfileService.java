package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.UserProfile;
import com.horrorcore.bankapp.exceptions.UserNotFoundException;
import com.horrorcore.bankapp.repositories.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing user profiles.
 * Provides methods to create, retrieve, update, and delete user profiles.
 * Uses `UserProfileRepository` for database operations.
 */
@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    /**
     * Retrieves a user profile by its unique ID.
     *
     * @param id The unique identifier of the user profile.
     * @return The `UserProfile` object if found.
     * @throws UserNotFoundException if no user profile is found with the given ID.
     */
    public UserProfile getUserProfileById(UUID id) {
        return userProfileRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    /**
     * Creates a new user profile.
     * If the user profile already exists, an exception is thrown.
     * If the ID is not provided, a new UUID is generated.
     *
     * @param userProfile The `UserProfile` object to be created.
     * @return The created `UserProfile` object.
     * @throws UserNotFoundException if the user profile already exists.
     */
    public UserProfile createUserProfile(UserProfile userProfile) {
        log.info("Creating User Profile: {}", userProfile);
        if (userProfile.getId() != null && userProfileRepository.existsById(userProfile.getId())) {
            throw new UserNotFoundException("User already exists");
        }
        log.info("Creating User Profile: {}", userProfile);
        if (userProfile.getId() == null) {
            userProfile.setId(UUID.randomUUID());
        }
        log.info("User Profile ID: {}", userProfile.getId());
        return userProfileRepository.insert(userProfile);
    }

    /**
     * Updates an existing user profile.
     * If the user profile does not exist, an exception is thrown.
     *
     * @param userProfile The `UserProfile` object with updated information.
     * @return The updated `UserProfile` object.
     * @throws UserNotFoundException if the user profile does not exist.
     */
    public UserProfile updateUserProfile(UserProfile userProfile) {
        if (!userProfileRepository.existsById(userProfile.getId())) {
            throw new UserNotFoundException("User not found");
        }
        return userProfileRepository.save(userProfile);
    }

    /**
     * Deletes a user profile by its unique ID.
     * If the user profile does not exist, an exception is thrown.
     *
     * @param id The unique identifier of the user profile to be deleted.
     * @throws UserNotFoundException if no user profile is found with the given ID.
     */
    public void deleteUserProfile(UUID id) {
        if (!userProfileRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userProfileRepository.deleteById(id);
    }
}
