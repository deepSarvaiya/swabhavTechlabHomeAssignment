package com.tss.modelInheritance.model;


public abstract class BillFromCard implements IBill {

    protected String cardNumber;
    protected String expiryDate;
    protected String cvv;
    protected double amount;

    public BillFromCard(String cardNumber, String expiryDate, String cvv, double amount) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.amount = amount;
    }

    @Override
    public boolean validatePaymentDetails() {
        if (!cardNumber.matches("\\d{16}")) {
            System.out.println("Card number must be exactly 16 digits and contain only numbers.");
            return false;
        }

        if (!cvv.matches("\\d{3}")) {
            System.out.println("CVV must be exactly 3 digits.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return false;
        }

        return true;
    }
}
