package com.tss.model;

public class InvoiceSolid {
	private int id;
	private String description;
	private double cost;
	private double discountPercent;

	public InvoiceSolid() {
	}

	public InvoiceSolid(int id, String description, double cost, double discountPercent) {
		this.id = id;
		this.description = description;
		this.cost = cost;
		this.discountPercent = discountPercent;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public double getCost() {
		return cost;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setDiscountPercent(double discountPercent) {
		this.discountPercent = discountPercent;
	}
}



