package com.tss.model;

//import java.util.Random;

public class Account2 {

	private int accountId = 1, balance;
	private String name, accountNumber;
	private AccountType accountType;

	private static int accountIdCount = 1;
//	private String prefix = "DEEPS1000";

//	
//	public Account2() {
//		balance =0;
//		name = "";
//	}
//	
	public Account2(int balance, String accountNumber, String name, AccountType accountType) {
		this.accountId = accountIdCount++;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.name = name;
		this.accountType = accountType;	
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	// private String generateUniqueAccountNumber() {}
//    private String generateUniqueAccountNumber(Account2[] existingAccounts) {
//        String generated;
//        boolean exists;
//        Random random = new Random();
//
//        do {
//            int randomNum = 100000 + random.nextInt(900000); // 6-digit 
//            generated = prefix + randomNum;
//            exists = false;
//
//            for (Account2 acc : existingAccounts) {
//                if (acc != null && generated.equals(acc.getAccountNumber())) {
//                    exists = true;
//                    break;
//                }
//            }
//
//        } while (exists);
//
//        return generated;
//    }
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public String getName() {
		return name;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public void deposit(int amount) {
		if (amount > 0) {
			balance = balance + amount;
			System.out.println("Deposited Successfully.");

		} else {
			System.out.println("Invalid amount");
		}
	}

	public boolean withdraw(int amount) {
		if (amount <= 0) {
//				System.out.println("Insufficient Balance in your account");
			return false;
		}
		if (balance - amount >= 500) {
			balance = balance - amount;
			System.out.println("Withdrawn successfully :" + amount);
			return true;
		} else {
			System.out.println("500 balanace required minimum.");
			return false;
		}
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", name=" + name + ", accountType=" + accountType + "]";
	}

}
