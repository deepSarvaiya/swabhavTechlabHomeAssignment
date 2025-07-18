package com.foodordering.model;

import java.io.Serializable;
import java.util.Map;

public class Order implements Serializable {
    private Map<FoodItem, Integer> itemsWithQuantity;
    private double totalAmount;
    private double discount;
    private double finalAmount;
    private String paymentMode;
    private String deliveryAgent;

    public Order(Map<FoodItem, Integer> itemsWithQuantity, double totalAmount, double discount, double finalAmount,
                 String paymentMode, String deliveryAgent) {
        this.itemsWithQuantity = itemsWithQuantity;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.finalAmount = finalAmount;
        this.paymentMode = paymentMode;
        this.deliveryAgent = deliveryAgent;
    }

    public Map<FoodItem, Integer> getItemsWithQuantity() {
        return itemsWithQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getDeliveryAgent() {
        return deliveryAgent;
    }

    public void printInvoice() {
        System.out.println("\n========================================");
        System.out.println("               INVOICE                  ");
        System.out.println("========================================");
        System.out.printf("%-25s %5s %10s\n", "Item", "Qty", "Subtotal");
        System.out.println("----------------------------------------");

        for (Map.Entry<FoodItem, Integer> entry : itemsWithQuantity.entrySet()) {
            FoodItem item = entry.getKey();
            int quantity = entry.getValue();
            double subtotal = item.getPrice() * quantity;
            System.out.printf("%-25s %5d %10.2f\n", item.getName(), quantity, subtotal);
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-30s ₹%8.2f\n", "Total Amount:", totalAmount);

        if (discount > 0.0) {
            System.out.printf("%-30s ₹%8.2f\n", "Discount Applied:", discount);
            System.out.printf("%-30s ₹%8.2f\n", "Final Payable Amount:", finalAmount);
        } else {
            System.out.printf("%-30s ₹%8.2f\n", "Final Payable Amount:", totalAmount);
        }

        System.out.println("----------------------------------------");
        System.out.printf("%-30s %s\n", "Payment Mode:", paymentMode);
        System.out.printf("%-30s %s\n", "Delivery Partner:", deliveryAgent);
        System.out.println("========================================\n");
    }

}
