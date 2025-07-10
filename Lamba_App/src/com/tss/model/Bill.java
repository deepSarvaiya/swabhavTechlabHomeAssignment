package com.tss.model;

public class Bill {
    private String itemName;
    private double basePrice;
    private double tax;
    private double shipping;
    private double totalAmount;

    public Bill(String itemName, double basePrice, double tax, double shipping, double totalAmount) {
        this.itemName = itemName;
        this.basePrice = basePrice;
        this.tax = tax;
        this.shipping = shipping;
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return
               "Item Name: " + itemName + "\n" +
               "Base Price: ₹" + basePrice + "\n" +
               "Tax (18%): ₹" + tax + "\n" +
               "Shipping: ₹" + shipping + "\n" +
               "---------------------\n" +
               "Total Amount: ₹" + totalAmount + "\n";
    }
}
