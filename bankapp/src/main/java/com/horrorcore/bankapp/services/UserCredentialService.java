package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.dto.AuthenticationRequest;
import com.horrorcore.bankapp.dto.AuthenticationResponse;
import com.horrorcore.bankapp.entities.UserCredential;
import com.horrorcore.bankapp.repositories.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service class for managing user credentials and authentication.
 * This class provides methods for user registration and login functionality.
 */
@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user in the system.
     *
     * @param request The authentication request containing the username and password.
     *                - `username`: The username of the new user.
     *                - `password`: The password of the new user.
     *
     * This method:
     * - Generates a unique ID for the user.
     * - Encodes the user's password for security.
     * - Assigns a default role of "ROLE_USER".
     * - Saves the user credentials in the database.
     */
    public String registerUser(AuthenticationRequest request) {
        UserCredential userCredential = new UserCredential();
        userCredential.setId(UUID.randomUUID());
        userCredential.setUsername(request.username());
        userCredential.setPassword(passwordEncoder.encode(request.password()));
        userCredential.setRole("ROLE_USER");
        userCredentialRepository.insert(userCredential);
        return userCredential.getId().toString();
    }

    /**
     * Authenticates a user and generates a JWT token.
     *
     * @param request The authentication request containing the username and password.
     *                - `username`: The username of the user attempting to log in.
     *                - `password`: The password of the user attempting to log in.
     *
     * @return An AuthenticationResponse containing the generated JWT token.
     *
     * This method:
     * - Authenticates the user using the provided credentials.
     * - Generates a JWT token for the authenticated user.
     */
    public AuthenticationResponse login(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        authenticationManager.authenticate(token);
        String jwt = jwtService.generateToken(request.username());
        return new AuthenticationResponse(jwt);
    }
}
