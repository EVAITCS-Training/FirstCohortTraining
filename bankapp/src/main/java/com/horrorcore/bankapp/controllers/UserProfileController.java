package com.horrorcore.bankapp.controllers;

import com.horrorcore.bankapp.annotations.LogExecutionTime;
import com.horrorcore.bankapp.entities.UserProfile;
import com.horrorcore.bankapp.services.UserProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//@Controller is used to mark a class as a Spring MVC controller
//@RestController is a convenience annotation that combines @Controller and @ResponseBody
@RestController
@RequestMapping("/api/v1/user-profile")
@Slf4j
public class UserProfileController {
    // for an MVC controller, we would use @GetMapping, @PostMapping.
    // for a REST controller, we would use @RequestMapping with the appropriate HTTP method
    // or GetMapping, PostMapping, PutMapping, DeleteMapping
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @LogExecutionTime
    @PostMapping(value = {"", "/"})
    public ResponseEntity<UserProfile> postNewUserProfile(@RequestBody UserProfile userProfile) {
        log.info("POST NEW USER PROFILE");
        log.info("User Profile: {}", userProfile);
        UserProfile createdUserProfile = userProfileService.createUserProfile(userProfile);
        log.info("Created User Profile: {}", createdUserProfile);
        return new ResponseEntity<>(createdUserProfile, HttpStatus.CREATED);
    }

    @LogExecutionTime
    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable String id) {
        log.info("GET USER PROFILE");
        log.info("User Profile ID: {}", id);
        UserProfile userProfile = userProfileService.getUserProfileById(UUID.fromString(id));
        log.info("User Profile: {}", userProfile);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    @LogExecutionTime
    @PutMapping("/")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile) {
        log.info("UPDATE USER PROFILE");
        log.info("User Profile: {}", userProfile);
        UserProfile updatedUserProfile = userProfileService.updateUserProfile(userProfile);
        log.info("Updated User Profile: {}", updatedUserProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable String id) {
        log.info("DELETE USER PROFILE");
        log.info("User Profile ID: {}", id);
        userProfileService.deleteUserProfile(UUID.fromString(id));
        log.info("Deleted User Profile ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
