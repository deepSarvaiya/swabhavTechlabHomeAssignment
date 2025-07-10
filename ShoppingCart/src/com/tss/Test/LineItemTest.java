package com.tss.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Customer;
import com.tss.model.LineItem;
import com.tss.model.Order;
import com.tss.model.Product;

public class LineItemTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Product> products = List.of(
            new Product(1, "Milk", 100, 10),
            new Product(2, "Laptop", 200, 20),
            new Product(3, "Book", 300, 15)
        );

        System.out.println("Welcome to the Shopping Application!");
        System.out.print("Enter Customer ID: ");
        int custId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String custName = sc.nextLine();

        System.out.print("Enter Order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        List<LineItem> lineItems = new ArrayList<>();
        int lineItemId = 1;

        boolean adding = true;
        while (adding) {
            System.out.println("\nAvailable Products:");
            for (Product p : products) {
                System.out.printf("ID: %d, Name: %s, Price: %.2f, Discount: %.0f%%\n",
                                  p.getId(), p.getName(), p.getPrice(), p.getDiscountPercent());
            }

            System.out.print("Enter Product ID to add to order: ");
            int pid = sc.nextInt();
            System.out.print("Enter Quantity: ");
            int qty = sc.nextInt();
            sc.nextLine();

            Product selectedProduct = products.stream().filter(p -> p.getId() == pid).findFirst().orElse(null);
            if (selectedProduct != null) {
                lineItems.add(new LineItem(lineItemId++, qty, selectedProduct));
            } else {
                System.out.println("Invalid Product ID.");
            }

            System.out.print("Do you want to add another item? (y/n): ");
            adding = sc.nextLine().equalsIgnoreCase("y");
        }

        Order order = new Order(orderId, new Date(), lineItems);
        Customer customer = new Customer(custId, custName, List.of(order));

        System.out.println("\n************ BILL ***************");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Order ID: " + order.getId());
        System.out.println("Order Date: " + order.getDate());

        System.out.printf("\n%-8s %-10s %-12s %-10s %-10s %-15s %-12s\n",
            "LineItem", "Product Id", "Item", "Qty", "Unit Price", "Discount %", "Total Cost");

        for (LineItem item : order.getItems()) {
            Product p = item.getProduct();
            System.out.printf("%-8d %-10d %-12s %-10d %-10.2f %-15.0f %-12.2f\n",
                item.getId(), p.getId(), p.getName(), item.getQuantity(),
                p.getPrice(), p.getDiscountPercent(), item.calculateLineItemCost());
        }

        System.out.println("\nTotal Order Price: " + order.calculateOrderPrice());
        System.out.println("\n********* Thank you for shopping with us! **********");
        sc.close();
    }
}