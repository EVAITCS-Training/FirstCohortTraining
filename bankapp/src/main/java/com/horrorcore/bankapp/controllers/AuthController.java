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

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserCredentialService userCredentialService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = userCredentialService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody AuthenticationRequest authenticationRequest) {
        userCredentialService.registerUser(authenticationRequest);
        return ResponseEntity.ok().build();
    }
}
