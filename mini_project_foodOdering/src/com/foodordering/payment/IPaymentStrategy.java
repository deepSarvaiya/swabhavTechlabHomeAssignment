package com.foodordering.payment;

public interface IPaymentStrategy {
    boolean pay(double amount);
}
