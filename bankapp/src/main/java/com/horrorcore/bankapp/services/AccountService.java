package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.Account;
import com.horrorcore.bankapp.exceptions.AccountNotFoundException;
import com.horrorcore.bankapp.exceptions.InsufficientBalanceException;
import com.horrorcore.bankapp.repositories.AccountRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

/**
 * Service class for managing accounts in the banking application.
 * Provides methods for creating accounts, retrieving accounts,
 * enabling/disabling accounts, and performing transactions like
 * withdrawals and deposits.
 */
@Service
public class AccountService {
    // Repository for accessing account data
    private AccountRepository accountRepository;

    /**
     * Constructor for AccountService.
     *
     * @param accountRepository the repository used for account data operations
     */
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new account with a unique ID.
     *
     * @param account the account object to be created
     * @return the created account with a unique ID
     */
    public Account createAccount(Account account) {
        UUID id = UUID.randomUUID();

        // Ensure the generated ID is unique
        do {
            if (accountRepository.existsById(id)) {
                System.out.println("Account already exists");
                id = UUID.randomUUID();
            }
        } while (accountRepository.existsById(id));

        account.setId(id);
        Account createdAccount = accountRepository.insert(account);
        return createdAccount;
    }

    /**
     * Retrieves all accounts associated with a specific user profile ID.
     *
     * @param userProfileId the ID of the user profile
     * @return a list of accounts associated with the user profile
     */
    public List<Account> getAllAccountsbyUserProfileID(UUID userProfileId) {
        return accountRepository.findAllByUserProfileId(userProfileId);
    }

    /**
     * Toggles the active status of an account (enable/disable).
     *
     * @param id the ID of the account to be updated
     * @return a message indicating the updated account status
     * @throws AccountNotFoundException if the account is not found
     */
    public String enableDisableAccount(UUID id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setActive(!account.isActive());
        accountRepository.save(account);
        return "Account status updated to " + (account.isActive() ? "active" : "inactive");
    }

    /**
     * Withdraws a specified amount from an account.
     *
     * @param id the ID of the account
     * @param amount the amount to withdraw
     * @return the updated account after the withdrawal
     * @throws AccountNotFoundException if the account is not found
     * @throws InsufficientBalanceException if the account has insufficient funds
     */
    protected Account withdraw(UUID id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return account;
    }

    /**
     * Deposits a specified amount into an account.
     *
     * @param id the ID of the account
     * @param amount the amount to deposit
     * @return the updated account after the deposit
     * @throws AccountNotFoundException if the account is not found
     */
    protected Account deposit(UUID id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return account;
    }
}