package com.horrorcore.bankapp.dto;

public record AuthenticationRequest(
        String username,
        String password
) {
}
