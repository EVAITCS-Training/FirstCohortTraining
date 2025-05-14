package com.horrorcore.bankapp.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String insufficientFunds) {
        super(insufficientFunds);
    }
}
