package com.tss.modelInheritance.model;

public class ElectricCar extends Car{
	
	private int batteryLevel;

	public ElectricCar(float price, int year, String model, int numberOfDoors, int batteryLevel) {
		super(price, year, model, numberOfDoors);
		this.batteryLevel = batteryLevel;
	}

	
	public void chargeBattery() {
		System.out.println("Battery is in Charjing");
	}
	
	public void displayBetteryStatus() {
		System.out.println("bettery Level : " + batteryLevel);
	}

}
