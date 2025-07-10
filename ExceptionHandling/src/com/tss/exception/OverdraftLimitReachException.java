package com.tss.exception;

public class OverdraftLimitReachException  extends RuntimeException{
	private double overdraftLimit;

	public OverdraftLimitReachException(double overdraftLimit) {
		super();
		this.overdraftLimit = overdraftLimit;
	}
	
	public String getMessage()
    {
    	return "You have reched to your overdraftlimit: "+overdraftLimit;
    }
}
