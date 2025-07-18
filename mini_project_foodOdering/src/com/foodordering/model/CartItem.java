package com.foodordering.model;

import java.io.Serializable;

public class CartItem implements Serializable {
	private FoodItem item;
	private int quantity;

	public CartItem(FoodItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}

	public FoodItem getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getSubtotal() {
		return item.getPrice() * quantity;
	}

	@Override
	public String toString() {
		return item.getName() + " x " + quantity + " = ₹" + getSubtotal();
	}
}
