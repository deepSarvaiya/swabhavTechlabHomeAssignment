package com.tss.modelInheritance.model;

public class BillFromCreditCard extends BillFromCard {

    public BillFromCreditCard(String cardNumber, String expiryDate, String cvv, double amount) {
        super(cardNumber, expiryDate, cvv, amount);
    }

    @Override
    public void payment() {
        System.out.println("Credit Card payment of " + amount + " processed from card: " + cardNumber);
    }
}
