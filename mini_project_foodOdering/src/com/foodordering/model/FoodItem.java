package com.foodordering.model;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String id; 
    private String name;
    private double price;
    private int quantity;
    private String menuType; 

    public FoodItem(String id, String name, double price, int quantity, String menuType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.menuType = menuType;
    }

    public FoodItem(String id, String name, double price, String menuType) {
        this(id, name, price, 1, menuType);
    }

    public String getId() {
        return id;
    }

    public String getMenuType() {
        return menuType;
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
        return id + ". " + name + " - ₹" + price + " (" + quantity + " pcs, " + menuType + ")";
    }
}
