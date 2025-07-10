package com.tss.model;

public class CreditCard2 implements IPayment{
	public void pay(int amount) {
		System.out.println("Pay using Credit Card Amount "+amount);
	}
}
