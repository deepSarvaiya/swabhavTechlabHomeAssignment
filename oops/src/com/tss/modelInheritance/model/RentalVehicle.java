package com.tss.modelInheritance.model;

public abstract class RentalVehicle {
	
	protected String vehicleNumber;
	protected float rentPerday;
	protected String fuelType;
	protected int numberOfDays;
	public RentalVehicle(String vehicleNumber, float rentPerday, String fuelType, int numberOfDays) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.rentPerday = rentPerday;
		this.fuelType = fuelType;
		this.numberOfDays = numberOfDays;
	}
	
	public abstract void calculate();
	
	public void display() {
		System.out.println("Vehicle Number:" + vehicleNumber);
		System.out.println("Rent Per Day" + rentPerday);
		System.out.println("fuelType:" + fuelType);
		
	}

}
