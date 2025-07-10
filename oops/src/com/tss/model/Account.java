package com.tss.model;

public class Account {

	private int accountId,accountNumber,balance;
	private String name;
	private AccountType accountType;
	
	public Account() {
		accountId = 0;
		accountNumber = 0;
		balance =0;
		name = "";
	}
	
	public Account(int accountId,int accountNumber,int balance,String name,AccountType accountType) {
		this.accountId = accountId;
		this.accountNumber = accountNumber ;
		this.balance =balance ;
		this.name = name;
		this.accountType = accountType;
	}
		
	
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		
		public void setAccountNumber(int accountNumber) {
			this.accountNumber = accountNumber;
		}
		public void setBalance(int balance) {
			this.balance = balance;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public int getAccountId() {
			return accountId;
		}

		public int getAccountNumber() {
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
		
		public void deposit (int amount) {
			if (amount >0) {
				balance = balance + amount; 
				System.out.println("Deposited Successfully.");
				
			}
			else {
				System.out.println("Invalid amount");
			}
		}
		
		public void withdraw (int amount) {
			if(amount <= 0 ) {
				System.out.println("Insufficient Balance in your account");
				return;
			}
			if (balance - amount >=500) {
				balance = balance - amount;
				System.out.println("Withdrawn successfully :" + amount);
			}
			else {
				System.out.println("500 balanace required minimum.");
			}
			
		}
		
		@Override
		public String toString() {
			return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", balance=" + balance
					+ ", name=" + name + ", accountType=" + accountType + "]";
		}

	}
	
	

