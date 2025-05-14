package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.Account;
import com.horrorcore.bankapp.exceptions.AccountNotFoundException;
import com.horrorcore.bankapp.exceptions.InsufficientBalanceException;
import com.horrorcore.bankapp.repositories.AccountRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class AccountService {
    // input the repositories
    private AccountRepository accountRepository;

    // constructor

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;

    }

    public Account createAccount(Account acoount) {

        UUID id = UUID.randomUUID();

        do {
            if (accountRepository.existsById(id)) {
                System.out.println("Account already exists");

                id = UUID.randomUUID();

            }
        } while (accountRepository.existsById(id));

        acoount.setId(id);
        Account createdAccount = accountRepository.insert(acoount);
        return createdAccount;
    }

    public List<Account> getAllAccountsbyUserProfileID(UUID userProfileId) {
        return accountRepository.findAllByUserProfileId(userProfileId);
    }

    public String enableDisableAccount(UUID id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setActive(!account.isActive());
        accountRepository.save(account);
        return "Account status updated to " + (account.isActive() ? "active" : "inactive");
    }

    protected Account withdraw(UUID id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return account;
    }

    protected Account deposit(UUID id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return account;
    }

}