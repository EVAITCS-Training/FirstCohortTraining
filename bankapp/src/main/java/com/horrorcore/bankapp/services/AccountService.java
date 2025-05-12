package com.horrorcore.bankapp.services;

import com.horrorcore.bankapp.entities.Account;
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

   public UUID createAccount(Account acoount){

       UUID id = UUID.randomUUID();

       do {
           if (accountRepository.existsById(id)) {
               System.out.println("Account already exists");

               id = UUID.randomUUID();

           }
       }while(accountRepository.existsById(id));

         acoount.setId(id);
         Account createdAccount = accountRepository.insert(acoount);
       return createdAccount.getId();
   }

   public List<Account> getAllAccountsbyUserProfileID(UUID userProfileId) {
       return accountRepository.findAllByUserProfileId(userProfileId);

   }
}

