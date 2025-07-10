package com.tss.modelInheritance.model;

public class Vehicle {
	
	private float price;
	private int year;
	private String model;
	
	
	public Vehicle(float price, int year, String model) {
//		super();
		this.price = price;
		this.year = year;
		this.model = model;
	}
	
	public void startEngine() {
		System.out.println("Started engine from vehicle");
	}
	
	public void stopEngine() {
		System.out.println("Started engine from vehicle");
	}
	
	public void displayInfo() {
		System.out.println("Price:" + price);
		System.out.println("year:" + year);
		System.out.println("Model:" + model);
	}
	
	
	
	
	
}
