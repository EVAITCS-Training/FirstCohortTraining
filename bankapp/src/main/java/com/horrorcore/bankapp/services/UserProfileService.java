package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.UserProfile;
import com.horrorcore.bankapp.exceptions.UserNotFoundException;
import com.horrorcore.bankapp.repositories.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile getUserProfileById(UUID id) {
        return userProfileRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

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

    public UserProfile updateUserProfile(UserProfile userProfile) {
        if (!userProfileRepository.existsById(userProfile.getId())) {
            throw new UserNotFoundException("User not found");
        }
        return userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(UUID id) {
        if (!userProfileRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userProfileRepository.deleteById(id);
    }
}
