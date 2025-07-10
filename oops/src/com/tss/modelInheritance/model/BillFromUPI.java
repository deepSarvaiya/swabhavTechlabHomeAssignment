package com.tss.modelInheritance.model;

public class BillFromUPI implements IBill {

    private String upiId;
    private double amount;

    public BillFromUPI(String upiId, double amount) {
        this.upiId = upiId;
        this.amount = amount;
    }

    @Override
    public void payment() {
        System.out.println(" UPI payment of " + amount + " processed for UPI ID: " + upiId);
    }

    @Override
    public boolean validatePaymentDetails() {
        return upiId != null && upiId.contains("@") && amount > 0;
    }
}
