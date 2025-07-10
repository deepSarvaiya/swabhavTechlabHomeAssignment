package com.tss.model;

public class ShopingCart {
	CreditCard paymentProcessor = new CreditCard();

    public void checkOut() {
        paymentProcessor.makePayment(300);
    }
}
