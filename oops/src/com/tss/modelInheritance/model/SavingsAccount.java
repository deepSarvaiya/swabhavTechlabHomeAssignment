package com.tss.modelInheritance.model;

public class SavingsAccount extends Account {
    private double minBalance;

    public SavingsAccount(int accNo, String name, double balance, double minBalance) {
        super(accNo, name, balance);
        this.minBalance = minBalance;
    }

	public void credit(double amount) {
		if(amount <= 0) {
			System.out.print("Invalid");
			return;
		}
		balance += amount;
		System.out.println("Amount credited successfully. New balance: " + balance);
	}
	
    public void debit(double amount) {
    	if(amount <= 0) {
			System.out.print("Invalid");
			return;
		}
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Amount debited successfully. New balance: " + balance);
        } else {
            System.out.println("Debit failed: Minimum balance must be maintained.");
        }
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Minimum Balance Required: " + minBalance);
    }
}