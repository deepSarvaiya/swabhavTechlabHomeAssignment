package com.foodordering.payment;

import java.util.Scanner;
import java.util.regex.Pattern;

public class CardPayment implements IPaymentStrategy {
    private final Scanner sc = new Scanner(System.in);

    @Override
    public boolean pay(double amount) {
        System.out.print("Enter 16-digit card number: ");
        String card = sc.nextLine();
        System.out.print("Enter CVV (3 digits): ");
        String cvv = sc.nextLine();

        if (Pattern.matches("\\d{16}", card) && Pattern.matches("\\d{3}", cvv)) {
            System.out.println("Card payment of ₹" + amount + " successful.");
            return true;
        } else {
            System.out.println("Invalid card details.");
            return false;
        }
    }
}
