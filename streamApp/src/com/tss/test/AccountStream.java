package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.tss.model.Account;

public class AccountStream {

    public static void main(String[] args) {

        List<Account> accounts = Arrays.asList(
                new Account(101, "Mahesh", 8500.50),
                new Account(102, "Ramesh", 12000.00),
                new Account(103, "Suresh", 6700.25),
                new Account(104, "Nandkishore", 15000.75),
                new Account(105, "Jay", 3000.00)
        );

        Account minBalanceAccount = accounts.stream()
                .min(Comparator.comparing(Account::getBalance))
                .orElse(null);
        System.out.println("a. Account with Minimum Balance: " + minBalanceAccount);

        Account maxBalanceAccount = accounts.stream()
                .max(Comparator.comparing(Account::getBalance))
                .orElse(null);
        System.out.println("b. Account with Maximum Balance: " + maxBalanceAccount);

        List<String> longNames = accounts.stream()
                .map(Account::getName)
                .filter(name -> name.length() > 6)
                .collect(Collectors.toList());
        System.out.println("c. Names > 6 characters: " + longNames);

        double totalBalance = accounts.stream()
                .mapToDouble(Account::getBalance)
                .sum();
        System.out.println("d. Total Balance of All Accounts: " + totalBalance);
    }
}