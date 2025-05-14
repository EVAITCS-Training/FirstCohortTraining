package com.horrorcore.bankapp.controllers;

import com.horrorcore.bankapp.annotations.LogExecutionTime;
import com.horrorcore.bankapp.entities.Account;
import com.horrorcore.bankapp.entities.UserProfile;
import com.horrorcore.bankapp.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // Add methods to handle account-related requests here
    @PostMapping (value = {"", "/"})

    public ResponseEntity<Account> postNewAccount (@RequestBody Account account) {

        Account createdAccount = accountService.createAccount(account);

        return ResponseEntity.status(201).body(createdAccount);



    }

    @GetMapping ("/{userProfileId}")
    public ResponseEntity<List<Account>> getAllAccountsByUserProfileId (@PathVariable String userProfileId) {
        List<Account> accounts = accountService.getAllAccountsbyUserProfileID(UUID.fromString(userProfileId));
        return ResponseEntity.ok(accounts);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> enableDisableAccount(@PathVariable String id) {
        String response = accountService.enableDisableAccount(UUID.fromString(id));
        return ResponseEntity.ok(response);
    }
}

