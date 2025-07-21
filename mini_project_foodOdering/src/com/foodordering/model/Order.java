package com.foodordering.model;

import java.io.Serializable;
import java.util.Map;

public class Order implements Serializable {
    private Map<FoodItem, Integer> itemsWithQuantity;
    private double totalAmount;
    private double regularDiscount;
    private double couponDiscount;
    private String couponCode;
    private double finalAmount;
    private String paymentMode;
    private String deliveryAgent;

    public Order(Map<FoodItem, Integer> itemsWithQuantity, double totalAmount, double regularDiscount, double couponDiscount, double finalAmount,
                 String paymentMode, String couponCode, String deliveryAgent) {
        this.itemsWithQuantity = itemsWithQuantity;
        this.totalAmount = totalAmount;
        this.regularDiscount = regularDiscount;
        this.couponDiscount = couponDiscount;
        this.finalAmount = finalAmount;
        this.paymentMode = paymentMode;
        this.couponCode = couponCode;
        this.deliveryAgent = deliveryAgent;
    }

    public Map<FoodItem, Integer> getItemsWithQuantity() {
        return itemsWithQuantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getRegularDiscount() {
        return regularDiscount;
    }

    public double getCouponDiscount() {
        return couponDiscount;
    }

    public String getCouponCode() {
        return couponCode;
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

        if (regularDiscount > 0.0) {
            System.out.printf("%-30s ₹%8.2f\n", "Regular Discount:", regularDiscount);
        }
        if (couponDiscount > 0.0) {
            System.out.printf("%-30s ₹%8.2f\n", "Coupon Discount:", couponDiscount);
            if (couponCode != null && !couponCode.isEmpty()) {
                System.out.printf("%-30s %s\n", "Coupon Code Used:", couponCode);
            }
        }
        double totalDiscount = regularDiscount + couponDiscount;
        if (totalDiscount > 0.0) {
            System.out.printf("%-30s ₹%8.2f\n", "Total Discount:", totalDiscount);
        }
        System.out.printf("%-30s ₹%8.2f\n", "Final Payable Amount:", finalAmount);

        System.out.println("----------------------------------------");
        System.out.printf("%-30s %s\n", "Payment Mode:", paymentMode);
        System.out.printf("%-30s %s\n", "Delivery Partner:", deliveryAgent);
        System.out.println("========================================\n");
    }

}
