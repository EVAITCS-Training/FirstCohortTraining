package com.horrorcore.bankapp.controllers;

import com.horrorcore.bankapp.dto.AuthenticationRequest;
import com.horrorcore.bankapp.dto.AuthenticationResponse;
import com.horrorcore.bankapp.services.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is a REST controller that handles authentication-related operations.
 * It provides endpoints for user login and registration.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    /**
     * Service for handling user credential operations such as login and registration.
     * It is automatically injected by Spring's dependency injection mechanism.
     */
    @Autowired
    private UserCredentialService userCredentialService;

    /**
     * Handles user login requests.
     *
     * @param authenticationRequest The login request containing user credentials (e.g., username and password).
     * @return A ResponseEntity containing the authentication response, such as a token or user details.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = userCredentialService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    /**
     * Handles user registration requests.
     *
     * @param authenticationRequest The registration request containing user credentials (e.g., username and password).
     * @return A ResponseEntity with an HTTP 200 status if the registration is successful.
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(userCredentialService.registerUser(authenticationRequest));
    }
}
