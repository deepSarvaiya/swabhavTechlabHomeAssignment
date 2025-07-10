package com.tss.modelInheritance.model;

public class BillFromPayPal implements IBill {

    private String email;
    private double amount;
    private String otp;

    public BillFromPayPal(String email, double amount, String otp) {
        this.email = email;
        this.amount = amount;
        this.otp = otp;
    }

    @Override
    public void payment() {
        System.out.println("PayPal payment of " + amount + " processed for: " + email);
    }

    @Override
    public boolean validatePaymentDetails() {
        if (email == null || !email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email.");
            return false;
        }

        if (amount <= 0) {
            System.out.println("Amount must be greater than 0.");
            return false;
        }

        if (otp == null || !otp.matches("\\d{4}")) {
            System.out.println("OTP must be exactly 4 digits.");
            return false;
        }

        return true;
    }
}
