package com.tss.test;

import java.util.Random;
import java.util.Scanner;

import com.tss.model.Account2;
import com.tss.model.AccountType;

public class AccountTest3 {


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter number of accounts to be created: ");
		int noOfAccount = scanner.nextInt();
		Account2[] accounts = new Account2[noOfAccount];

		int noOfOpenedAccount = 0;

		boolean running = true;
		while (running) {
			int choice = 0;
			if (noOfOpenedAccount < noOfAccount) {
				System.out.println("\nChoose an option:");
				System.out.println("1. Create Account");
				System.out.println("2. Deposit");
				System.out.println("3. Withdraw");
				System.out.println("4. Display All Accounts");
				System.out.println("5. Transfer");
				System.out.println("6. Show Balance");
				System.out.println("7. Exit");

				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();

			} else {
				System.out.println("\nChoose an option:");
//			System.out.println("1. Create Account");
				System.out.println("2. Deposit");
				System.out.println("3. Withdraw");
				System.out.println("4. Display All Accounts");
				System.out.println("5. Transfer");
				System.out.println("6. Show Balance");
				System.out.println("7. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				if(choice == 1) {
					
					System.out.println("Please Enter Valid Number!");
					continue;
				}
			}


			switch (choice) {
			case 1:
				if(readDetails(accounts, scanner, noOfOpenedAccount)) {
					noOfOpenedAccount++;
				};
				break;

			case 2:
				System.out.print("Enter Account Id to deposit into: ");
				int depositAccountId = scanner.nextInt();
				Account2 depositAcc = findAccount(accounts, depositAccountId);
				if (depositAcc != null) {
					System.out.print("Enter amount to deposit: ");
					int depositAmount = scanner.nextInt();
					depositAcc.deposit(depositAmount);
				} else {
					System.out.println("Account not found.");
				}
				break;

			case 3:
				System.out.print("Enter Account Id to withdraw from: ");
				int withdrawAccountId = scanner.nextInt();
				Account2 withdrawAcc = findAccount(accounts, withdrawAccountId);
				if (withdrawAcc != null) {
					System.out.print("Enter amount to withdraw: ");
					int withdrawAmount = scanner.nextInt();
					if (withdrawAcc.withdraw(withdrawAmount)) {
//						System.out.println("Withdrawal successful!");
					} else {
						System.out.println("Insufficient balance.");
					}
				} else {
					System.out.println("Account not found.");
				}
				break;

			case 4:
				for (Account2 acc : accounts) {
					System.out.println(acc);
				}
				break;

			case 5:
				System.out.print("Enter source account id: ");
				int sourceAccountId = scanner.nextInt();
				Account2 sourceAcc = findAccount(accounts, sourceAccountId);

				System.out.print("Enter destination account ID: ");
				int destAccountId = scanner.nextInt();
				Account2 destAcc = findAccount(accounts, destAccountId);

				if (sourceAcc != null && destAcc != null) {
					System.out.print("Enter amount to transfer: ");
					int transferAmount = scanner.nextInt();
					if (sourceAcc.withdraw(transferAmount)) {
						destAcc.deposit(transferAmount);
						System.out.println("Transfer successful!");
					} else {
						System.out.println("Transfer failed. Insufficient balance.");
					}
				} else {
					System.out.println("One or both accounts not found.");
				}
				break;

			case 6:
				System.out.print("Enter Account Id: ");
				int accId = scanner.nextInt();
				Account2 acc = findAccount(accounts, accId);
				if (acc != null) {
					System.out.print("current Balance: " + acc.getBalance());
				} else {
					System.out.println("Account not found.");
				}
				break;
			
			case 7:
				System.out.println("Exiting...");
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}

		scanner.close();
	}

	public static boolean readDetails(Account2[] account, Scanner scanner, int i) {
		System.out.println("\nCreating account #" + (i + 1));
//		System.out.print("Account Id: ");
//		int accountId = scanner.nextInt();
//
//		System.out.print("Account Number: ");
//		int accountNumber = scanner.nextInt();

		System.out.print("Customer Name: ");
		String name = scanner.next();

		System.out.print("Initial Balance: ");
		int balance = scanner.nextInt();
		
	    String accountNumber = generateUniqueAccountNumber(account);


		boolean acc = false;
		while (!acc) {
			System.out.print("Enter Account Type (1-SAVINGS, 2-CURRENT, 3-FD): ");
			int typeNumber = scanner.nextInt();
			if (typeNumber == 1) {
				account[i] = new Account2( balance,accountNumber, name, AccountType.SAVINGS);
				acc = true;
			} else if (typeNumber == 2) {
				account[i] = new Account2(balance,accountNumber, name, AccountType.CURRENT);
				acc = true;
			} else if (typeNumber == 3) {
				account[i] = new Account2( balance,accountNumber, name, AccountType.FD);
				acc = true;
			} else {
				System.out.println("Invalid account type. Try again.");
			}
		}
		System.out.println("Account Created!");
	    System.out.println("Account Number: " + account[i].getAccountNumber());
	    System.out.println("Account Id: " + account[i].getAccountId());
	    

		return true;
		}

	public static Account2 findAccount(Account2[] accounts, int accountId) {
		for (Account2 acc : accounts) {
			if (acc != null && acc.getAccountId() == accountId) {
				return acc;
			}
		}
		return null;
	}
	
	public static String generateUniqueAccountNumber(Account2[] accounts) {
	    Random random = new Random();
	    String prefix = "DEEPS1000";
	    String accountNumber;
	    boolean exists;

	    do {
	        int randomDigits = 100000 + random.nextInt(900000); // 6-digit
	        accountNumber = prefix + randomDigits;

	        exists = false;
	        for (Account2 acc : accounts) {
	            if (acc != null && accountNumber.equals(acc.getAccountNumber())) {
	                exists = true;
	                break;
	            }
	        }

	    } while (exists);

	    return accountNumber;
	}

}
