package com.foodordering.payment;

import java.util.Scanner;
import java.util.regex.Pattern;

public class UPIPayment implements IPaymentStrategy {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public boolean pay(double amount) {
        System.out.print("Enter UPI ID (e.g., name@bank): ");
        String upi = sc.nextLine();
        if (Pattern.matches("^[\\w.]+@[\\w]+$", upi)) {
            System.out.println("UPI payment of ₹" + amount + " successful.");
            return true;
        } else {
            System.out.println("Invalid UPI ID.");
            return false;
        }
    }
}
