package com.tss.model;

public class FixedDepositFestival {

	    private int accountNumber;
	    private String name;
	    private double principal;
	    private int duration;
	    private IFestival festival;

	    public FixedDepositFestival(int accountNumber, String name, double principal, int duration, IFestival festival) {
	        this.accountNumber = accountNumber;
	        this.name = name;
	        this.principal = principal;
	        this.duration = duration;
	        this.festival = festival;
	    }

	    // Getters and Setters
	    public int getAccountNumber() {
	        return accountNumber;
	    }

	    public void setAccountNumber(int accountNumber) {
	        this.accountNumber = accountNumber;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrincipal() {
	        return principal;
	    }

	    public void setPrincipal(double principal) {
	        this.principal = principal;
	    }

	    public int getDuration() {
	        return duration;
	    }

	    public void setDuration(int duration) {
	        this.duration = duration;
	    }

	    public IFestival getFestival() {
	        return festival;
	    }

	    public void setFestival(IFestival festival) {
	        this.festival = festival;
	    }
	    public double calculateSimpleInterest() {
	        return (principal * duration * festival.getRate()) / 100;
	    }
}
