package com.horrorcore;

public class Account {
    private int id;
    private String username;
    private String password;
    private double balance;
    private String accountType;

    public Account() {}

    public Account(int id, String username, String password, double balance, String accountType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.accountType = accountType;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String username() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double balance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String accountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
