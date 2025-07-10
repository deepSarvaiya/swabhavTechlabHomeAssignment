package com.tss.modelInheritance.model;

public class BillFromDebitCard extends BillFromCard {

    public BillFromDebitCard(String cardNumber, String expiryDate, String cvv, double amount) {
        super(cardNumber, expiryDate, cvv, amount);
    }

    @Override
    public void payment() {
        System.out.println("Debit Card payment of " + amount + " processed from card: " + cardNumber);
    }
}
