package com.tss.test;

import java.util.Scanner;

import com.tss.model.Account;
import com.tss.model.AccountType;

public class AccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Account account = null;
		
		while(true) {
			System.out.println("1. Create Account");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdrwan");
			System.out.println("5. Exit");
			System.out.println("Enter your choice :");
			int option = scanner.nextInt();
		
		
		switch(option) {
		case 1:
			System.out.println("Account Id:");
			int accountId = scanner.nextInt();
			System.out.println("Account Number:");
			int accountNumber = scanner.nextInt();
			System.out.println("Customer Name:");
			String name = scanner.next();
			System.out.println("Initial Balance:");
			int balance = scanner.nextInt();
			
			boolean acc = false;
			while(!acc) {
				System.out.println("Account Type 1-SAVINGS , 2-CURRENT , 3-FD");
				int typeNumber = scanner.nextInt();
				if (typeNumber == 1) {
					account = new Account(accountId,accountNumber,balance,name,AccountType.SAVINGS); 
					acc = true;
				}
				else if(typeNumber == 2) {
					account = new Account(accountId,accountNumber,balance,name,AccountType.CURRENT); 
					acc = true;
				}
				else if (typeNumber == 3){
					account = new Account(accountId,accountNumber,balance,name,AccountType.FD); 
					acc = true;
				}
				else {
					System.out.println("Account Type Invalid");
				}
			}
			System.out.println("Account Created !!!");
			break;
			
		case 2:
			if (account != null) {
				System.out.println("Current Balance :" + account.getBalance());
			}
			else {
				System.out.println("NO ACCOUNT DETACTED . OPEN NEW ACCOUNT");
			}
			
			break;
			
		case 3:
			if(account != null) {
				System.out.println("Enter Deposit Amount :");
				int deposit = scanner.nextInt();
				account.deposit(deposit);
			}
			
			else {
				System.out.println("NO ACCOUNT DETACTED . OPEN NEW ACCOUNT");
			}
			
			break;
			
		case 4:
			if(account != null) {
				System.out.println("Enter amount to withdrawn :");
				int withdrawn = scanner.nextInt();
				account.withdraw(withdrawn);
			}
			else {
				System.out.println("NO ACCOUNT DETACTED . OPEN NEW ACCOUNT");
			}
			
		case 5:
			System.out.println("Exit!!");
			scanner.close();
			System.exit(0);
			
			break;
			
			default :
				System.out.println("SORRYY TRY AGAIN!!");
	}
	}

	}

}
