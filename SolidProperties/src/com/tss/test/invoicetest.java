

package com.tss.test;

import com.tss.model.invoice;

import java.util.ArrayList;
import java.util.Scanner;

public class invoicetest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<invoice> invoices = new ArrayList<>();

        System.out.print("How many invoices do you want to enter? ");
        int n = scanner.nextInt();
        scanner.nextLine(); 
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for invoice " + (i + 1) + ":");

            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Cost: ");
            double cost = scanner.nextDouble();

            System.out.print("Discount Percentage: ");
            double discountPercent = scanner.nextDouble();

            
            invoice inv = new invoice(id, description, cost, discountPercent);
            invoices.add(inv);
        }

        System.out.println("\n========================Invoice Details ==========================");
        for (invoice inv : invoices) {
            inv.print();
            System.out.println();
        }

        scanner.close();
    }
}
