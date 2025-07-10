package com.tss.modelInheritance.model;

public class CurrentAccount extends Account{
	
	   private double overdraftLimit;

	    public CurrentAccount(int accNo, String name, double balance, double overdraftLimit) {
	        super(accNo, name, balance);
	        this.overdraftLimit = overdraftLimit;
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
	        if (balance - amount >= -overdraftLimit) {
	            balance -= amount;
	            System.out.println("Amount debited successfully. New balance: " + balance);
	        } else {
	            System.out.println("Debit failed: Exceeds overdraft limit.");
	        }
	    }

	    @Override
	    public void displayDetails() {
	        super.displayDetails();
	        System.out.println("Overdraft Limit: " + overdraftLimit);
	    }

}
