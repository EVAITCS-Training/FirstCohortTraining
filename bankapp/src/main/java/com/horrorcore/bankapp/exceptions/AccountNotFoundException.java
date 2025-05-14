package com.horrorcore.bankapp.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountNotFound) {
        super(accountNotFound);
    }
}
