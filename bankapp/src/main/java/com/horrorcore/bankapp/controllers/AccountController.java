package com.horrorcore.bankapp.controllers;

import com.horrorcore.bankapp.annotations.LogExecutionTime;
import com.horrorcore.bankapp.entities.Account;
import com.horrorcore.bankapp.entities.UserProfile;
import com.horrorcore.bankapp.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * This controller handles all HTTP requests related to accounts.
 * It provides endpoints for creating accounts, retrieving accounts by user profile ID,
 * and enabling/disabling accounts.
 */
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Constructor for AccountController.
     * @param accountService The service layer that handles account-related business logic.
     */
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Endpoint to create a new account.
     * Accepts an Account object in the request body and returns the created account.
     *
     * @param account The account details to be created.
     * @return ResponseEntity containing the created account and HTTP status 201 (Created).
     */
    @PostMapping (value = {"", "/"})
    public ResponseEntity<Account> postNewAccount (@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(201).body(createdAccount);
    }

    /**
     * Endpoint to retrieve all accounts associated with a specific user profile ID.
     *
     * @param userProfileId The UUID of the user profile whose accounts are to be retrieved.
     * @return ResponseEntity containing a list of accounts and HTTP status 200 (OK).
     */
    @GetMapping ("/{userProfileId}")
    public ResponseEntity<List<Account>> getAllAccountsByUserProfileId (@PathVariable String userProfileId) {
        List<Account> accounts = accountService.getAllAccountsbyUserProfileID(UUID.fromString(userProfileId));
        return ResponseEntity.ok(accounts);
    }

    /**
     * Endpoint to enable or disable an account.
     * Accepts the account ID as a path variable and toggles its status.
     *
     * @param id The UUID of the account to be enabled/disabled.
     * @return ResponseEntity containing a message about the operation result and HTTP status 200 (OK).
     */
    @PatchMapping("/{id}")
    public ResponseEntity<String> enableDisableAccount(@PathVariable String id) {
        String response = accountService.enableDisableAccount(UUID.fromString(id));
        return ResponseEntity.ok(response);
    }
}

