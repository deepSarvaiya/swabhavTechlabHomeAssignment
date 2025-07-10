package com.tss.test;

import java.util.Scanner;
import com.tss.model.Account;
import com.tss.model.AccountType;

public class AccountTest2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of accounts to be created: ");
        int n = scanner.nextInt();
        Account[] accounts = new Account[n];

        for (int i = 0; i < n; i++) {
            readDetails(accounts, scanner, i);
        }

        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number to deposit into: ");
                    int depositAccNum = scanner.nextInt();
                    Account depositAcc = findAccount(accounts, depositAccNum);
                    if (depositAcc != null) {
                        System.out.print("Enter amount to deposit: ");
                        int depositAmount = scanner.nextInt();
                        depositAcc.deposit(depositAmount);
                        
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Account Number to withdraw from: ");
                    int withdrawAccNum = scanner.nextInt();
                    Account withdrawAcc = findAccount(accounts, withdrawAccNum);
                    if (withdrawAcc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        int withdrawAmount = scanner.nextInt();
                        withdrawAcc.withdraw(withdrawAmount);
                        System.out.println("Withdrawal successful!");
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 3:
                    for (Account acc : accounts) {
                        System.out.println(acc);
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }

    public static void readDetails(Account[] account, Scanner scanner, int i) {
        System.out.println("\nCreating account #" + (i + 1));
        System.out.print("Account Id: ");
        int accountId = scanner.nextInt();

        System.out.print("Account Number: ");
        int accountNumber = scanner.nextInt();

        System.out.print("Customer Name: ");
        String name = scanner.next();

        System.out.print("Initial Balance: ");
        int balance = scanner.nextInt();

        boolean acc = false;
        while (!acc) {
            System.out.print("Enter Account Type (1-SAVINGS, 2-CURRENT, 3-FD): ");
            int typeNumber = scanner.nextInt();
            if (typeNumber == 1) {
                account[i] = new Account(accountId, accountNumber, balance, name, AccountType.SAVINGS);
                acc = true;
            } else if (typeNumber == 2) {
                account[i] = new Account(accountId, accountNumber, balance, name, AccountType.CURRENT);
                acc = true;
            } else if (typeNumber == 3) {
                account[i] = new Account(accountId, accountNumber, balance, name, AccountType.FD);
                acc = true;
            } else {
                System.out.println("Invalid account type. Try again.");
            }
        }
        System.out.println("Account Created!");
    }

    public static Account findAccount(Account[] accounts, int accountNumber) {
        for (Account acc : accounts) {
            if (acc != null && acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }
}
