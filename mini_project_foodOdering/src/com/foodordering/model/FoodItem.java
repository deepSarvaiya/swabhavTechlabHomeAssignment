package com.foodordering.model;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private int id;
    private String name;
    private double price;
    private int quantity;
    public FoodItem(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public FoodItem(int id, String name, double price) {
        this(id, name, price, 1);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - ₹" + price + " (" + quantity + " pcs)";
    }
}
