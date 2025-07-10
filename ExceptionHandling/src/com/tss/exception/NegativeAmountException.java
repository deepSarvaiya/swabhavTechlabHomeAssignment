package com.tss.exception;

public class NegativeAmountException extends RuntimeException {
	private double amount;
    
    
    public NegativeAmountException(double amount) {
		super();
		this.amount = amount;
	}


	public String getMessage()
    {
    	return "You have entered Negative Amount: "+amount;
    }
}
