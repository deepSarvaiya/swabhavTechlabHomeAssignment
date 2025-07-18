package com.foodordering.payment;

import java.util.Scanner;

import com.foodordering.food.MenuServiceImpl;

public class PaymentProcessor {
    private final Scanner sc = new Scanner(System.in);

    public boolean processPayment(double totalAmount) {
    	double discount = 0;
    	double percent = MenuServiceImpl.getDiscountPercentage();
    	if (totalAmount > 500) {
    	    discount = (percent / 100.0) * totalAmount;
    	    System.out.println("Discount applied: ₹" + discount + " (" + percent + "%)");
    	    totalAmount -= discount;
    	}

        System.out.println("Final amount to pay: ₹" + totalAmount);
        System.out.println("Choose Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Card");
        System.out.println("3. Cash");

        int choice = sc.nextInt();
        sc.nextLine();

        IPaymentStrategy strategy;
        switch (choice) {
            case 1:
                strategy = new UPIPayment();
                break;
            case 2:
                strategy = new CardPayment();
                break;
            case 3:
                strategy = new CashPayment();
                break;
            default:
                System.out.println("Invalid option.");
                return false;
        }

        return strategy.pay(totalAmount);
    }
}
