package com.horrorcore;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // Uncomment the following lines to test exception handling
//        FileReader fr = new FileReader("test.txt");
//
//        try{
//            System.out.println(2/0);
//        } catch (Exception e){
//            System.err.println(e);
//        }

        Scanner sc = new Scanner(System.in);
        List<Account> accounts = new ArrayList<Account>();


        while (true) {
            System.out.println("""
                    Welcome to the Banking System
                    Menu Options:
                    1. Create Account
                    2. Deposit
                    3. Withdraw
                    4. Check Balance
                    5. Exit
                    """);
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    Account account = new Account();
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double balance = 0;
                    try {
                        balance = sc.nextDouble();
                        if (balance < 0) {
                            throw new IllegalArgumentException("Initial balance cannot be negative.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                        sc.nextLine(); // Consume the invalid input
                        continue; // Skip to the next iteration of the loop
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        sc.nextLine(); // Consume the invalid input
                        continue; // Skip to the next iteration of the loop
                    } finally {
                        // This block will always execute, even if an exception occurs
                        System.out.println("Attempting to create account...");
                    }
                    sc.nextLine(); // Consume the newline character
                    System.out.print("Enter account type (savings/current): ");
                    String accountType = sc.nextLine();
                    account.setUsername(username);
                    account.setPassword(password);
                    account.setBalance(balance);
                    account.setAccountType(accountType);
                    System.out.println("Account created successfully!");
                    accounts.add(account);
                    break;
                case 2:
                    break;

                case 4:
                    try{
                        System.out.println("Enter account ID to check balance: ");
                        int accountId = sc.nextInt();
                        boolean accountFound = false;
                        for (Account acc : accounts) {
                            if (acc.id() == accountId) {
                                System.out.println("Account balance: " + acc.balance());
                                accountFound = true;
                                break;
                            }
                        }
                        if (!accountFound) {
                            throw new AccountNotFoundException("Invalid account ID.");
                        }
                    } catch (AccountNotFoundException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error occurred: " + e.getMessage());
                    }
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // throws
    }
}