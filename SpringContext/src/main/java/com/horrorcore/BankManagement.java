package com.horrorcore;

import java.util.Scanner;

public class BankManagement {
    private int id;
    private int checkingAccount;
    private int savingsAccount;
    private Scanner scan;

    public BankManagement() {
        this.id = 0;
        this.checkingAccount = 0;
        this.savingsAccount = 0;
        this.scan = new Scanner(System.in);
    }

    public BankManagement(int id, int checkingAccount, int savingsAccount, Scanner scan) {
        this.id = id;
        this.checkingAccount = checkingAccount;
        this.savingsAccount = savingsAccount;
        this.scan = scan;
    }
}
