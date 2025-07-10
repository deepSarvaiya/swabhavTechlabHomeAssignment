package com.tss.model;

public class UPIPayment implements IPayment{
	public void pay(int amount) {
		System.out.println("Pay using UPI Amount "+amount);
	}
}
