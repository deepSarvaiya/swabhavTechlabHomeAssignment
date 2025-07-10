package com.tss.modelInheritance.model;

public class Car extends Vehicle{
	
	private int numberOfDoors;

	public Car(float price, int year, String model, int numberOfDoors) {
		super(price, year, model);
		this.numberOfDoors = numberOfDoors;
	}
	
	public void lockDoors() {
		System.out.println("Dor is locked");
	}
	
	public void unlockDoors() {
		System.out.println("Dor is unlock");
	}
}
