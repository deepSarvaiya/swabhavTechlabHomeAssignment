package com.foodordering.payment;

public class CashPayment implements IPaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("Cash on Delivery selected. Amount to pay: ₹" + amount);
        return true;
    }
}
