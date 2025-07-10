package com.tss.modelInheritance.test;

import java.util.Scanner;

import com.tss.modelInheritance.model.BillFromCreditCard;
import com.tss.modelInheritance.model.BillFromDebitCard;
import com.tss.modelInheritance.model.BillFromPayPal;
import com.tss.modelInheritance.model.BillFromUPI;
import com.tss.modelInheritance.model.IBill;

public class BillTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IBill bill = null;
        boolean valid = false;
        int retries = 0;

        System.out.println("Enter Amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if(amount < 0)
        {
        	System.out.println("Enter Valid Amount");
        	return;
        }
        System.out.println("Choose Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. PayPal");
        System.out.println("4. UPI");

        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
    
        
        while (!valid && retries < 5) {
            switch (choice) {
                case 1: case 2:
                    System.out.println("Enter Card Number:");
                    String cNumber = scanner.nextLine();

                    System.out.println("Enter Expiry Date (MM/YY):");
                    String cExpiry = scanner.nextLine();

                    System.out.println("Enter CVV:");
                    String cCVV = scanner.nextLine();

              
                    if(choice == 1) {
                    	 bill = new BillFromCreditCard(cNumber, cExpiry, cCVV, amount);
                         break;
                    }
                    bill = new BillFromDebitCard(cNumber, cExpiry, cCVV, amount);
                    break;
                   


                case 3:
                    System.out.println("Enter PayPal Email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter 4 digit OTP that send in your mail:");
                    String otp = scanner.nextLine();

                    bill = new BillFromPayPal(email, amount, otp);
                    break;

                case 4:
                    System.out.println("Enter UPI ID:");
                    String upiId = scanner.nextLine();

                  

                    bill = new BillFromUPI(upiId, amount);
                    break;

                default:
                    System.out.println("Invalid option. Exiting.");
                    return;
            }

            if (bill != null && bill.validatePaymentDetails()) {
                bill.payment();
                valid = true;
                System.out.println("THank You");
                System.exit(0);
            } else {
                retries++;
                System.out.println("Invalid details. Attempts left: " + (5 - retries));
                System.out.println();
            }
        }

        if (!valid) {
            System.out.println("Too many failed attempts. Payment failed.");
        }
    }
}
